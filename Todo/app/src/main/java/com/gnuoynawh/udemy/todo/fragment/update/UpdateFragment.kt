package com.gnuoynawh.udemy.todo.fragment.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gnuoynawh.udemy.todo.R
import com.gnuoynawh.udemy.todo.data.models.Priority
import com.gnuoynawh.udemy.todo.fragment.SharedViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.findViewById<EditText>(R.id.edt_custom_title).setText(args.todo.title)
        view.findViewById<EditText>(R.id.edt_description).setText(args.todo.description)
        view.findViewById<Spinner>(R.id.spinner_custom_priorities).setSelection(parsePriority(args.todo.priority))
        view.findViewById<Spinner>(R.id.spinner_custom_priorities).onItemSelectedListener = mSharedViewModel.listener

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    private fun parsePriority(priority: Priority): Int {
        return when(priority) {
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}