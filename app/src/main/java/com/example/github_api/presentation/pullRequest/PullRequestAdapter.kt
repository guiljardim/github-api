package com.example.github_api.presentation.pullRequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.github_api.databinding.ItemPullRequestBinding
import com.example.github_api.domain.model.PullRequest

class PullRequestAdapter(
    private var listOfPullRequest: List<PullRequest>?,
) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPullRequestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listOfPullRequest?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listOfPullRequest.let {
            holder.bindView(it?.get(position))
        }
    }

    class ViewHolder(private val binding: ItemPullRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(pullRequest: PullRequest?) {
            with(binding) {

            }
        }
    }
}