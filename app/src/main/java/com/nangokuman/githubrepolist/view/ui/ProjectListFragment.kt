package com.nangokuman.githubrepolist.view

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nangokuman.githubrepolist.MainActivity
import com.nangokuman.githubrepolist.R
import com.nangokuman.githubrepolist.databinding.FragmentProjectListBinding
import com.nangokuman.githubrepolist.service.model.Project
import com.nangokuman.githubrepolist.view.adapter.ProjectAdapter
import com.nangokuman.githubrepolist.view.callback.ProjectClickCallback
import com.nangokuman.githubrepolist.viewModel.ProjectListViewModel

class ProjectListFragment : Fragment() {
    companion object {
        val TAG = ProjectListFragment::class.java.simpleName
    }

    private var projectAdapter: ProjectAdapter? = null
    private var binding: FragmentProjectListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)
        projectAdapter = ProjectAdapter(projectClickCallback)

        requireNotNull(binding).projectList.adapter = projectAdapter
        requireNotNull(binding).isLoading = true
        return requireNotNull(binding).root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        viewModel.projectListObservable.observe(this, Observer { projects ->
            if (projects != null) {
                requireNotNull(binding).isLoading = false
                projectAdapter?.setProjectList(projects)
            }
        })
    }

    private val projectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
                && activity is MainActivity
            ) {
                (activity as MainActivity).show(project)
            }
        }
    }

}
