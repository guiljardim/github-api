package com.example.github_api

import com.example.github_api.data.mapper.mapRemoteRepositoryToDomain
import com.example.github_api.data.model.RepositoriesResponse
import com.example.github_api.domain.model.Repository
import org.junit.Assert
import org.junit.Test

class RepositoriesMapperTest {

    @Test
    fun `should map Repositories remote to Repository`() {

        val actual =
            Repository(
                1, "", "", "", "", 2, 1, true, Repository.Owner("", 1, "", "", "", ""),
            )

        Assert.assertEquals(
            getRepositoriesResponse().items.first().mapRemoteRepositoryToDomain(),
            actual
        )

    }


    private fun getRepositoriesResponse(): RepositoriesResponse =
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
}


