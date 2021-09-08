package com.proway.projeto_crud_di.model

import com.google.gson.annotations.SerializedName

data class GithubUserModel(
    @SerializedName("login")
    val login: String?,
    @SerializedName("avatar_url")
    val avatar: String?
)
