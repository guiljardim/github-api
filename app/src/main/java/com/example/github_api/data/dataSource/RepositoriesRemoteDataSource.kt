package com.example.github_api.data.dataSource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.github_api.data.RepositoriesService
import com.example.github_api.data.model.RepositoriesResponse.RepositoryResponse
import kotlinx.coroutines.flow.Flow

class RepositoriesRemoteDataSource(
    private val repositoriesService: RepositoriesService
) {
    fun getRepositories(): Flow<PagingData<RepositoryResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                RepositoriesPagingSource(service = repositoriesService)
            }
        ).flow
    }
}