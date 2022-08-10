package com.example.github_api.presentation.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.github_api.domain.model.Repository
import com.example.github_api.domain.useCase.GetRepositoryUseCase
import com.example.github_api.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(private val getRepositoryUseCase: GetRepositoryUseCase) :
    ViewModel() {

    private val _repositories = MutableLiveData<Resource<PagingData<Repository>>>()

    val repositories: LiveData<Resource<PagingData<Repository>>>
        get() = _repositories

    fun getRepositories() =
        getRepositoryUseCase.invoke()
            .map { pagingData ->
                pagingData
            }


}