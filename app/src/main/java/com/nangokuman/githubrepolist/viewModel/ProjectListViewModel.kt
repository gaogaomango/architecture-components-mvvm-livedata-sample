package com.nangokuman.githubrepolist.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.nangokuman.githubrepolist.R
import com.nangokuman.githubrepolist.service.model.Project
import com.nangokuman.githubrepolist.service.repository.ProjectRepository

class ProjectListViewModel(application: Application) : AndroidViewModel(application) {
    val projectListObservable: LiveData<List<Project>> = ProjectRepository.instance.getProjectList(
        getApplication<Application>().getString(
            R.string.github_user_name
        )
    )
}