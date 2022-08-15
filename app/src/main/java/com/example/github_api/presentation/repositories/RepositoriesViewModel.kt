package com.example.github_api.presentation.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.github_api.domain.model.Repository
import com.example.github_api.domain.useCase.GetRepositoryUseCase
import com.example.github_api.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(private val getRepositoryUseCase: GetRepositoryUseCase) :
    ViewModel() {

    private val _repositories = MutableLiveData<Resource<PagingData<Repository>>>()

    val repositories: LiveData<Resource<PagingData<Repository>>>
        get() = _repositories

    fun getRepositories() {
        with(viewModelScope) {
            launch {
                getRepositoryUseCase.invoke(viewModelScope)
                    .onStart {
                        _repositories.postValue(Resource.loading())
                    }.catch {
                        _repositories.postValue(Resource.error())
                    }.collect {
                        _repositories.postValue(Resource.success(it))
                    }
            }


        }
    }
}