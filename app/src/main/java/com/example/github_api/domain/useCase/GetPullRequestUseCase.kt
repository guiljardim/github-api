package com.example.github_api.domain.useCase

import com.example.github_api.domain.model.PullRequest
import com.example.github_api.domain.repository.PullRequestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPullRequestUseCase @Inject constructor(
    private val pullRequestRepository: PullRequestRepository
) {
    suspend operator fun invoke(owner: String, repository: String): Flow<List<PullRequest>?> =
        flow { emit(pullRequestRepository.getPullRequest(owner, repository)) }
}