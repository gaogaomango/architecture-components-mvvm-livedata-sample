package com.nangokuman.githubrepolist.view.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nangokuman.githubrepolist.R
import com.nangokuman.githubrepolist.databinding.FragmentProjectDetailsBinding
import com.nangokuman.githubrepolist.viewModel.ProjectViewModel

import java.util.Objects


private const val KEY_PROJECT_ID = "project_id"

class ProjectDetailsFragment : Fragment() {

    private var binding: FragmentProjectDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false)

        // Inflate the layout for this fragment
        return requireNotNull(this.binding).root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        assert(arguments != null)
        val factory = ProjectViewModel.Factory(
            Objects.requireNonNull<FragmentActivity>(activity).application,
            requireNotNull(arguments).getString(KEY_PROJECT_ID)
        )

        val viewModel = ViewModelProviders.of(this, factory).get(ProjectViewModel::class.java)

        requireNotNull(binding).projectViewModel = viewModel
        requireNotNull(binding).isLoading = true

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectViewModel) {
        viewModel.observableProject.observe(this, android.arch.lifecycle.Observer { project ->
            if (project != null) {
                requireNotNull(binding).isLoading = false
                viewModel.setProject(project)
            }
        })
    }

    companion object PUT {

        fun forProject(projectID: String): ProjectDetailsFragment {
            val fragment = ProjectDetailsFragment()
            val args = Bundle()

            args.putString(KEY_PROJECT_ID, projectID)
            fragment.arguments = args

            return fragment
        }
    }
}
