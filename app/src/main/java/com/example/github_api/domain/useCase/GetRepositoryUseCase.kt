package com.example.github_api.domain.useCase

import androidx.paging.PagingData
import com.example.github_api.domain.model.Repository
import com.example.github_api.domain.repository.RepositoriesListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryUseCase @Inject constructor(private val repositoriesListRepository: RepositoriesListRepository) {

    operator fun invoke(): Flow<PagingData<Repository>?> =
        repositoriesListRepository.getRepositories()


}