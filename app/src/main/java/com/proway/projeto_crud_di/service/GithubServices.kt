package com.proway.projeto_crud_di.service

import com.proway.projeto_crud_di.model.GithubRepositoryResponse
import com.proway.projeto_crud_di.model.GithubUserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubServices {

    /**
     * Usamos o @Query para deixar os paremetros dinamicos de acordo com a documentacao da API
     */
    @GET("/search/repositories")
    suspend fun fetchRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Response<GithubRepositoryResponse>

    @GET("/users")
    suspend fun fetchUsers(
    ): Response<List<GithubUserModel>>


}