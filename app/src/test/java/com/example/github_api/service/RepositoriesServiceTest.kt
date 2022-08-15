package com.example.github_api.service

import com.example.github_api.data.api.RepositoriesService
import com.example.github_api.data.model.RepositoriesResponse
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
class RepositoryServiceTest {
    private lateinit var service: RepositoriesService
    private lateinit var server: MockWebServer


    @Before
    fun setup() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RepositoriesService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }


    @Test
    fun `Assert get repositories remote response structure match JSON Server response`() {
        val expected = getRepositoriesResponse()
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(expected))
        server.enqueue(response)

        runBlocking {
            val actual = service.getRepositories("", 2)


            Assert.assertEquals(Gson().toJson(expected), Gson().toJson(actual))
        }
    }
}


private fun getRepositoriesResponse() =
    RepositoriesResponse(
        "20",
        false,
        listOf(
            RepositoriesResponse.RepositoryResponse(
                1,
                "",
                "",
                "",
                true,
                "",
                1,
                2,
                RepositoriesResponse.OwnerResponse(
                    "",
                    1,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    true
                )
            )
        )
    )



