package com.example.github_api.domain.model

data class Repository(
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val private: Boolean,
    val owner: Owner
) {
    data class Owner(
        val login: String,
        val id: Int,
        val avatar_url: String,
        val gravatar_id: String,
        val url: String,
        val type: String,
    )
}
