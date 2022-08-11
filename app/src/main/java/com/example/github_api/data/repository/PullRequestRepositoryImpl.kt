package com.example.github_api.data.repository

import com.example.github_api.data.dataSource.PullRequestRemoteDataSource
import com.example.github_api.data.mapper.mapRemotePullRequestToDomain
import com.example.github_api.domain.model.PullRequest
import com.example.github_api.domain.repository.PullRequestRepository
import javax.inject.Inject

class PullRequestRepositoryImpl @Inject constructor(private val pullRequestRemoteDataSource: PullRequestRemoteDataSource) :
    PullRequestRepository {
    override suspend fun getPullRequest(owner: String, repository: String): List<PullRequest> {
        return pullRequestRemoteDataSource.invoke(owner, repository).mapRemotePullRequestToDomain()
    }
}