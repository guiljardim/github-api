package com.example.github_api.service

import com.example.github_api.data.api.PullRequestsService
import com.example.github_api.data.model.PullRequestResponse
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection


@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class PullRequestServiceTest {
    private lateinit var service: PullRequestsService
    private lateinit var server: MockWebServer


    @Before
    fun setup() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PullRequestsService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }


    @Test
    fun `Assert get pull request remote response structure match JSON Server response`() {
        val expected = getPullRequestResponse()
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(expected))
        server.enqueue(response)

        runBlocking {
            val actual = service.getPullRequest("", "")


            Assert.assertEquals(Gson().toJson(expected), Gson().toJson(actual))
        }
    }
}


private fun getPullRequestResponse() =
    listOf(PullRequestResponse("title", PullRequestResponse.UserResponse("", 1), "", ""))

