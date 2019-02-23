package com.nangokuman.githubrepolist.view.callback

import com.nangokuman.githubrepolist.service.model.Project

interface ProjectClickCallback {
    fun onClick(project: Project)
}