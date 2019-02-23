package com.nangokuman.githubrepolist.view.callback

import android.databinding.BindingAdapter
import android.view.View

object CustomBinderAdapter {

    @BindingAdapter("visibleGone")
    @JvmStatic
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}