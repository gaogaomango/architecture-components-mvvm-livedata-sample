package com.nangokuman.githubrepolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nangokuman.githubrepolist.service.model.Project
import com.nangokuman.githubrepolist.view.ProjectListFragment
import com.nangokuman.githubrepolist.view.ui.ProjectDetailsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            val fragment= ProjectListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment, ProjectListFragment.TAG)
                .commit()
        }
    }

    fun show(project: Project) {
        val fragment = ProjectDetailsFragment.forProject(project.name)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("project")
            .replace(R.id.fragment_container, fragment, null)
            .commit()
    }
}
