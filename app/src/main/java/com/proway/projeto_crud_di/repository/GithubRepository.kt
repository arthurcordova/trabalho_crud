package com.proway.projeto_crud_di.repository

import com.proway.projeto_crud_di.model.GithubRepositoryResponse
import com.proway.projeto_crud_di.model.GithubUserModel
import com.proway.projeto_crud_di.service.GithubServices
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject

class GithubRepository @Inject constructor(private val services: GithubServices) {

    suspend fun getRepositories(): Deferred<GithubRepositoryResponse?> {
        return CoroutineScope(Dispatchers.Default).async {
            val response =
                services.fetchRepositories(language = "language:Kotlin", page = 1, sort = "stars")
            processData(response)
        }
    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getUsers(): List<GithubUserModel>? {
        return withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
            val response =
                services.fetchUsers()
            processData(response)
        }
    }


}