package com.example.github_api.data.dataSource

import com.example.github_api.data.api.PullRequestsService
import com.example.github_api.data.model.PullRequestResponse
import javax.inject.Inject

class PullRequestRemoteDataSource @Inject constructor(
    private val service: PullRequestsService
) {
    suspend operator fun invoke(owner: String, repository: String): List<PullRequestResponse> {
        return service.getPullRequest(owner, repository)
    }
}