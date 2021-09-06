package com.proway.projeto_crud_di.di

import com.proway.projeto_crud_di.service.GithubServices
import com.proway.projeto_crud_di.service.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class Providers {

    @Provides
    fun provideGithubService(): GithubServices = RetrofitService.getGithubServices()


}