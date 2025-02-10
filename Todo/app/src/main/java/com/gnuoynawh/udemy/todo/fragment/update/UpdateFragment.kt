package com.gnuoynawh.udemy.todo.fragment.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gnuoynawh.udemy.todo.R
import com.gnuoynawh.udemy.todo.data.models.TodoData
import com.gnuoynawh.udemy.todo.data.viewmodel.TodoViewModel
import com.gnuoynawh.udemy.todo.databinding.FragmentUpdateBinding
import com.gnuoynawh.udemy.todo.fragment.SharedViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mTodoViewModel: TodoViewModel by viewModels()

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.todo = args.todo

        binding.spinnerCustomPriorities.onItemSelectedListener = mSharedViewModel.listener

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_save -> updateDataTodo()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateDataTodo() {
        val mTitle = binding.edtCustomTitle.text.toString()
        val mPriority = binding.spinnerCustomPriorities.selectedItem.toString()
        val mDescription = binding.edtCustomDescription.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)

        if (validation) {
            val updatedItem = TodoData(
                args.todo.id,
                mTitle,
                mSharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mTodoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(), "Successful updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmItemRemoval() {
        AlertDialog.Builder(requireContext())
            .setPositiveButton("Yes") { _, _ ->
                mTodoViewModel.deleteItem(args.todo)
                Toast.makeText(
                    requireContext(),
                    "Successfully Removed: ${args.todo.title}!",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            .setNegativeButton("No") { _, _ -> }
            .setTitle("Delete '${args.todo.title}'?")
            .setMessage("Are you sure you want to remove '${args.todo.title}'?")
            .create()
            .show()
    }
}