package com.example.github_api.data.mapper

import com.example.github_api.data.model.RepositoriesResponse.RepositoryResponse
import com.example.github_api.domain.model.Repository


fun RepositoryResponse.mapRemoteRepositoryToDomain() = Repository(
    this.id,
    this.node_id,
    this.name,
    this.full_name,
    this.description,
    this.stargazers_count,
    this.forks_count,
    this.private,
    Repository.Owner(
        this.owner.login,
        this.owner.id,
        this.owner.avatar_url,
        this.owner.gravatar_id,
        this.owner.url,
        this.owner.type
    )
)


