package com.example.github_api.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.github_api.data.dataSource.RepositoriesRemoteDataSource
import com.example.github_api.data.mapper.mapRemoteRepositoryToDomain
import com.example.github_api.domain.repository.RepositoriesListRepository
import com.example.github_api.domain.model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoriesListRepositoryImpl(
    private val remoteDataSource: RepositoriesRemoteDataSource,
) : RepositoriesListRepository {

    override fun getRepositories(): Flow<PagingData<Repository>> {
        return remoteDataSource.getRepositories()
            .map { pagingData ->
                pagingData.map { remoteRepository ->
                    remoteRepository.mapRemoteRepositoryToDomain()
                }
            }
    }
}


