package com.example.github_api.data.model

data class RepositoriesResponse(
    val total_count: String,
    val incomplete_results: Boolean,
    val items: List<RepositoryResponse>
) {
    data class RepositoryResponse(
        val id: Int,
        val node_id: String,
        val name: String,
        val full_name: String,
        val private: Boolean,
        val owner: OwnerResponse
    )

    data class OwnerResponse(
        val login: String,
        val id: Int,
        val node_id: String,
        val avatar_url: String,
        val gravatar_id: String,
        val url: String,
        val html_url: String,
        val followers_url: String,
        val following_url: String,
        val gists_url: String,
        val starred_url: String,
        val subscriptions_url: String,
        val organizations_url: String,
        val repos_url: String,
        val events_url: String,
        val received_events_url: String,
        val type: String,
        val site_admin: Boolean
    )
}
