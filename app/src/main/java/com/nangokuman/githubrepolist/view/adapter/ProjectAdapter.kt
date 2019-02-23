package com.nangokuman.githubrepolist.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nangokuman.githubrepolist.R
import com.nangokuman.githubrepolist.databinding.ProjectListItemBinding
import com.nangokuman.githubrepolist.service.model.Project
import com.nangokuman.githubrepolist.view.callback.ProjectClickCallback

class ProjectAdapter(private val projectClickCallback: ProjectClickCallback?) :
    RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    private var projectList: List<Project>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.project_list_item,
            parent,
            false
        ) as ProjectListItemBinding

        binding.callback = projectClickCallback
        return ProjectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (projectList == null) 0 else requireNotNull(projectList).size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.project = requireNotNull(projectList)[position]
        holder.binding.executePendingBindings()
    }

    fun setProjectList(projectList: List<Project>) {
        if (this.projectList == null) {
            this.projectList = projectList
            notifyItemRangeInserted(0, projectList.size)
            return
        }

        val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return requireNotNull(this@ProjectAdapter.projectList)[oldItemPosition].id == projectList[newItemPosition].id
            }

            override fun getOldListSize(): Int {
                return requireNotNull(this@ProjectAdapter.projectList).size
            }

            override fun getNewListSize(): Int {
                return projectList.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val project = projectList[newItemPosition]
                val old = projectList[oldItemPosition]

                return project.id == old.id && project.git_url == old.git_url
            }
        })

        this.projectList = projectList

        result.dispatchUpdatesTo(this)
    }

    class ProjectViewHolder(val binding: ProjectListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}