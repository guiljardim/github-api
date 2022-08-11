package com.example.github_api.presentation.pullRequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github_api.R
import com.example.github_api.databinding.FragmentPullRequestBinding
import com.example.github_api.domain.model.PullRequest
import com.example.github_api.util.Resource


const val OWNER = "OWNER"
const val REPOSITORY = "REPOSITORY"

class PullRequestFragment : Fragment() {

    private val viewModel: PullRequestViewModel by viewModels()
    private lateinit var binding: FragmentPullRequestBinding
    private var pullRequestAdapter: PullRequestAdapter? = null
    private lateinit var owner: String
    private lateinit var repository: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPullRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        viewModel.getPullRequest(
            arguments?.getString(OWNER).toString(),
            arguments?.getString(REPOSITORY).toString()
        )
    }

    private fun observer() {
        viewModel.pullRequest.observe(viewLifecycleOwner) {
            when (it.status) {

                Resource.Status.LOADING -> {
                    showProgress(true)
                }

                Resource.Status.ERROR -> {
                    showProgress(false)
                    Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT)
                        .show()
                    findNavController().popBackStack()

                }

                Resource.Status.SUCCESS -> {
                    showProgress(false)
                    it.data?.let { pullRequests -> createList(pullRequests) }
                }
            }
        }
    }

    private fun showProgress(show: Boolean) {
        binding.progressBar.isVisible = show
        binding.recycleViewPullRequests.isVisible = !show
    }


    private fun createList(ListOfPullRequest: List<PullRequest>) {
        pullRequestAdapter = context?.let { PullRequestAdapter(ListOfPullRequest) }
        binding.recycleViewPullRequests.layoutManager = LinearLayoutManager(context)
        binding.recycleViewPullRequests.adapter = pullRequestAdapter

    }
}