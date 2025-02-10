package com.gnuoynawh.udemy.todo.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gnuoynawh.udemy.todo.R
import com.gnuoynawh.udemy.todo.data.viewmodel.TodoViewModel
import com.gnuoynawh.udemy.todo.databinding.FragmentListBinding
import com.gnuoynawh.udemy.todo.fragment.SharedViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private val mToDoViewModel: TodoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Data Binding
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel

        //set RecyclerView
        setRecyclerView()

        // Observe LiveData
        mToDoViewModel.getAllData.observe(viewLifecycleOwner) { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
        }

        //set Menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete_all) {
            confirmRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun confirmRemoval() {
        AlertDialog.Builder(requireContext())
            .setPositiveButton("Yes") { _, _ ->
                mToDoViewModel.deleteAll()
                Toast.makeText(
                    requireContext(),
                    "Successfully Removed Everything!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("No") { _, _ -> }
            .setTitle("Delete everything?")
            .setMessage("Are you sure you want to remove everything?")
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}