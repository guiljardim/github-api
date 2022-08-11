package com.example.github_api.domain.repository

import com.example.github_api.domain.model.PullRequest

interface PullRequestRepository {

    suspend fun getPullRequest(owner: String, repository: String): List<PullRequest>
}