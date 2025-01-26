package com.gnuoynawh.udemy.todo.fragment.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gnuoynawh.udemy.todo.R
import com.gnuoynawh.udemy.todo.data.models.Priority
import com.gnuoynawh.udemy.todo.data.models.TodoData
import com.gnuoynawh.udemy.todo.data.viewmodel.TodoViewModel
import com.gnuoynawh.udemy.todo.fragment.SharedViewModel

class AddFragment : Fragment() {

    private lateinit var mView: View
    private val mTodoViewModel: TodoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_add, container, false)
        mView.findViewById<Spinner>(R.id.spinner_priorities).onItemSelectedListener = mSharedViewModel.listener

        setHasOptionsMenu(true)

        return mView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataTodo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataTodo() {
        val mTitle = mView.findViewById<EditText>(R.id.edt_title).text.toString()
        val mPriority = mView.findViewById<Spinner>(R.id.spinner_priorities).selectedItem.toString()
        val mDescription = mView.findViewById<EditText>(R.id.edt_description).text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if (validation) {
            val newData = TodoData(
                0,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mTodoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successful added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }


}