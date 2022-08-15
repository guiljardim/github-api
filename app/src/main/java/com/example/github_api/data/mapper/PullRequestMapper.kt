package com.example.github_api.data.mapper

import com.example.github_api.data.model.PullRequestResponse
import com.example.github_api.domain.model.PullRequest


fun List<PullRequestResponse>.mapRemotePullRequestToDomain() =
    this.map { PullRequest(it.title, it.user.login, it.created_at, it.body) }