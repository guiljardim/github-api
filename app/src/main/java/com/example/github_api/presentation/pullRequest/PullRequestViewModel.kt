package com.example.github_api.presentation.pullRequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_api.domain.model.PullRequest
import com.example.github_api.domain.useCase.GetPullRequestUseCase
import com.example.github_api.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(private val getPullRequestUseCase: GetPullRequestUseCase) :
    ViewModel() {

    private val _pullRequest = MutableLiveData<Resource<List<PullRequest>>>()

    val pullRequest: LiveData<Resource<List<PullRequest>>>
        get() = _pullRequest

    fun getPullRequest(owner: String, repository: String) {
        with(viewModelScope) {
            launch {
                getPullRequestUseCase.invoke(owner, repository)
                    .onStart {
                        _pullRequest.postValue(Resource.loading())
                    }.catch {
                        _pullRequest.postValue(Resource.error())
                    }.collect {
                        _pullRequest.postValue(Resource.success(it))
                    }
            }
        }

    }

}