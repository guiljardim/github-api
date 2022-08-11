package com.example.github_api.data.model

data class PullRequestResponse(
    val title: String, val user: UserResponse, val created_at: String?, val body: String
) {

    data class UserResponse(val login: String, val id: Int)
}