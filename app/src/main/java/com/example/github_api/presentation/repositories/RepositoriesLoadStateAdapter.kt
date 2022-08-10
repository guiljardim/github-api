package com.example.github_api.presentation.repositories

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class RepositoriesLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<RepositoryLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: RepositoryLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RepositoryLoadStateViewHolder {
        return RepositoryLoadStateViewHolder.create(parent, retry)
    }
}