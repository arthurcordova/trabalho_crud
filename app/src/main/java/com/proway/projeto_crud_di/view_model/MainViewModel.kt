package com.proway.projeto_crud_di.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proway.projeto_crud_di.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {



    fun getRepositories() {
        viewModelScope.launch {
            val responseModel = repository.getRepositories().await()
            println(responseModel)
        }
    }

}