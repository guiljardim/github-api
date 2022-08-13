package com.example.github_api.mapper

import com.example.github_api.data.mapper.mapRemotePullRequestToDomain
import com.example.github_api.data.model.PullRequestResponse
import com.example.github_api.domain.model.PullRequest
import org.junit.Assert
import org.junit.Test

class PullRequestMapperTest {

    @Test
    fun `should map PullRequest remote to Pull Request`() {

        val actual =
            listOf(
                PullRequest(
                    "title", "login", "123", "body"
                )
            )

        Assert.assertEquals(
            getPullRequestResponse().mapRemotePullRequestToDomain(),
            actual
        )

    }
}

fun getPullRequestResponse() = listOf(
    PullRequestResponse(
        "title",
        PullRequestResponse.UserResponse("login", 1),
        "123",
        "body"
    )
)
