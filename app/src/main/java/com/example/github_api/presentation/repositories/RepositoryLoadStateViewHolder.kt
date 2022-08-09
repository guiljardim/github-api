package com.example.github_api.presentation.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.github_api.R
import com.example.github_api.databinding.ItemLoadStateBinding

class RepositoryLoadStateViewHolder(
    private val binding: ItemLoadStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnMoviesRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.tvMoviesErrorDescription.text = loadState.error.localizedMessage
        }
        binding.progressMoviesLoadMore.isVisible = loadState is LoadState.Loading
        binding.btnMoviesRetry.isVisible = loadState is LoadState.Error
        binding.tvMoviesErrorDescription.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): RepositoryLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_state, parent, false)
            val binding = ItemLoadStateBinding.bind(view)
            return RepositoryLoadStateViewHolder(binding, retry)
        }
    }
}