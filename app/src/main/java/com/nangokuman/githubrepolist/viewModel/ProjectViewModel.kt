package com.nangokuman.githubrepolist.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import com.nangokuman.githubrepolist.R
import com.nangokuman.githubrepolist.service.model.Project
import com.nangokuman.githubrepolist.service.repository.ProjectRepository

class ProjectViewModel(application: Application, projectId: String) : AndroidViewModel(application) {

    val observableProject: LiveData<Project> = ProjectRepository
        .instance
        .getProjectDetails(application.getString(R.string.github_user_name), projectId)

    var project = ObservableField<Project>()

    fun setProject(project: Project) {
        this.project.set(project)
    }

    class Factory(private val application: Application, private val projectId: String) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ProjectViewModel(application, projectId) as T

        }
    }
}