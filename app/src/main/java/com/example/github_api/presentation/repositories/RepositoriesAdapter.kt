package com.example.github_api.presentation.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.github_api.databinding.ItemRepositoryBinding
import com.example.github_api.domain.model.Repository

class RepositoriesAdapter :
    PagingDataAdapter<Repository, RepositoryViewHolder>(RepositoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}