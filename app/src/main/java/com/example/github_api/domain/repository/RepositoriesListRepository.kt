package com.example.github_api.domain.repository

import androidx.paging.PagingData
import com.example.github_api.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoriesListRepository {

    fun getRepositories(): Flow<PagingData<Repository>>
}