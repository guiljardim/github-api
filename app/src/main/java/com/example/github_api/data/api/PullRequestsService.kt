package com.example.github_api.data.api

import com.example.github_api.data.model.PullRequestResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestsService {

    @GET("/repos/{owner}/{repository}/pulls")
    suspend fun getPullRequest(
        @Path("owner") owner: String,
        @Path("repository") repository: String,
    ): List<PullRequestResponse>
}