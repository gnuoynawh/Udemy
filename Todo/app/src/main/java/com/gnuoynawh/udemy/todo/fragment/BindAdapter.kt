package com.gnuoynawh.udemy.todo.fragment

import android.view.View
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.gnuoynawh.udemy.todo.R
import com.gnuoynawh.udemy.todo.data.models.Priority
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindAdapter {

    companion object {

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
            when(emptyDatabase.value!!) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(spinner: Spinner, priority: Priority) {
            when(priority) {
                Priority.HIGH -> spinner.setSelection(0)
                Priority.MEDIUM -> spinner.setSelection(1)
                Priority.LOW -> spinner.setSelection(2)
            }
        }
    }
}