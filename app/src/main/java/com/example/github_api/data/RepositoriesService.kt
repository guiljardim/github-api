package com.example.github_api.data

import com.example.github_api.data.model.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoriesService {

    @GET("/search/repositories?q=language:Java")
    suspend fun getRepositories(
        @Query("sort") sort: String,
        @Query("page") page: Int,
    ): RepositoriesResponse
}