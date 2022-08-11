package com.example.github_api.presentation.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import com.example.github_api.R
import com.example.github_api.databinding.ItemRepositoryBinding
import com.example.github_api.domain.model.Repository
import com.example.github_api.presentation.pullRequest.OWNER
import com.example.github_api.presentation.pullRequest.REPOSITORY

class RepositoriesAdapter() :
    PagingDataAdapter<Repository, RepositoryViewHolder>(RepositoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val holder = RepositoryViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

        holder.binding.root.setOnClickListener { view ->
            getItem(holder.adapterPosition)?.let { repository ->
                view.findNavController().navigate(
                    R.id.action_matches_fragment_to_matches_details_fragment,
                    Bundle().apply {
                        putString(OWNER, repository.owner.login)
                        putString(REPOSITORY, repository.name)
                    })
            }
        }
        return holder

    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}