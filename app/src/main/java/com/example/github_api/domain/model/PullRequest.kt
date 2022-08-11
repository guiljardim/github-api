package com.example.github_api.domain.model

data class PullRequest(
    val title: String,
    val login: String,
    val created_at: String?,
    val body: String
)
