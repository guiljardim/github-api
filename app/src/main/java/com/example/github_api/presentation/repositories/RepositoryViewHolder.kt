package com.example.github_api.presentation.repositories

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.github_api.databinding.ItemRepositoryBinding
import com.example.github_api.domain.model.Repository

class RepositoryViewHolder(
    private val binding: ItemRepositoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: Repository?) {
        binding.imageViewAvatar.load(repository?.owner?.avatar_url)
        binding.textViewUsername.text = repository?.owner?.login
        binding.textViewRepositoryName.text = repository?.full_name
        binding.textViewRepositoryDescription.text = repository?.description
        binding.textViewForks.text = repository?.forks.toString()
        binding.textViewStars.text = repository?.stars.toString()
    }
}