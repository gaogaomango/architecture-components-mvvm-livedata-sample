package com.nangokuman.githubrepolist.service.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.text.Spannable
import android.util.Log
import com.nangokuman.githubrepolist.service.model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectRepository private constructor() {

    private var githubService: GithubService

    companion object Factory {
        val TAG = ProjectRepository::class.java.simpleName

        val instance: ProjectRepository
            @Synchronized get() {
                return ProjectRepository()
            }
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(HTTPS_API_GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        githubService = retrofit.create(GithubService::class.java)
    }

    fun getProjectList(userId: String): LiveData<List<Project>> {
        val data = MutableLiveData<List<Project>>()

        githubService.getProjectList(userId).enqueue(object : Callback<List<Project>> {
            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>?) {
                if (response == null) {
                    return
                }
                data.postValue(response.body())
                Log.d(TAG, "getProjectList: " + response.body().toString())
            }

            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                data.postValue(null)
                Log.d(TAG, "getProjectList: onFailure")
                t.stackTrace
            }

        })
        return data
    }

    fun getProjectDetails(userId: String, projectName: String): LiveData<Project> {
        val data = MutableLiveData<Project>()

        githubService.getProjectDetails(userId, projectName).enqueue(object : Callback<Project>{
            override fun onResponse(call: Call<Project>, response: Response<Project>) {
                if(response == null) {
                    return
                }
data.postValue(response.body())
                Log.d(TAG, "getProjectDetails: " + response.body().toString())
            }

            override fun onFailure(call: Call<Project>, t: Throwable) {
                data.postValue(null)
                Log.d(TAG, "getProjectList: onFailure")
                t.stackTrace
            }
        })
        return data
    }

}