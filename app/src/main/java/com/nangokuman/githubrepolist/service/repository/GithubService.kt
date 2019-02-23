package com.nangokuman.githubrepolist.service.repository

import com.nangokuman.githubrepolist.service.model.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val HTTPS_API_GITHUB_URL = "https://api.github.com/"

internal interface GithubService {

    @GET("/users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Project>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Project>

}