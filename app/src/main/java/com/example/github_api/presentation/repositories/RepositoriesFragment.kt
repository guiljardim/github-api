package com.example.github_api.presentation.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github_api.databinding.FragmentRepositoriesBinding
import com.example.github_api.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RepositoriesFragment : Fragment() {

    private var adapter: RepositoriesAdapter? = null

    private lateinit var binding: FragmentRepositoriesBinding

    private val viewModel: RepositoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRepositories()

    }

    private fun initView() {
        val dividerItemDecoration = DividerItemDecoration(
            binding.recycleViewRepositories.context,
            DividerItemDecoration.VERTICAL
        )
        binding.recycleViewRepositories.addItemDecoration(dividerItemDecoration)
        adapter = RepositoriesAdapter()

        binding.recycleViewRepositories.layoutManager = LinearLayoutManager(context)

        binding.recycleViewRepositories.adapter = adapter?.withLoadStateHeaderAndFooter(
            header = RepositoriesLoadStateAdapter { adapter?.retry() },
            footer = RepositoriesLoadStateAdapter { adapter?.retry() }
        )
        adapter?.addLoadStateListener { loadState -> renderUi(loadState) }

    }

    private fun observe() {
        viewModel.repositories.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    viewLifecycleOwner.lifecycleScope.launch {
                        it.data?.let { it1 -> adapter?.submitData(it1) }
                    }
                }
            }
        }
    }


    private fun renderUi(loadState: CombinedLoadStates) {
        val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter?.itemCount == 0

        binding.recycleViewRepositories.isVisible = !isListEmpty
        binding.textViewRepositoryNotFound.isVisible = isListEmpty
        binding.recycleViewRepositories.isVisible = loadState.source.refresh is LoadState.NotLoading
        binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
    }


}