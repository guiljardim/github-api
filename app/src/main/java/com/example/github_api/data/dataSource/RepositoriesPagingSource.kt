package com.example.github_api.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.github_api.data.api.RepositoriesService
import com.example.github_api.data.model.RepositoriesResponse.RepositoryResponse
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1
const val NETWORK_PAGE_SIZE = 25

class RepositoriesPagingSource(
    private val service: RepositoriesService
) : PagingSource<Int, RepositoryResponse>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryResponse> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getRepositories(
                page = pageIndex,
                sort = "stars"
            )
            val movies = response.items
            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, RepositoryResponse>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}