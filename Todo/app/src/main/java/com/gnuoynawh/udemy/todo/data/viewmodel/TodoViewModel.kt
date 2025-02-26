package com.gnuoynawh.udemy.todo.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gnuoynawh.udemy.todo.data.TodoDao
import com.gnuoynawh.udemy.todo.data.TodoDatabase
import com.gnuoynawh.udemy.todo.data.models.TodoData
import com.gnuoynawh.udemy.todo.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {

    //private val todoDao: TodoDao = TodoDatabase.getDatabase(application).todoDao()
    private val repository: TodoRepository

    val getAllData: LiveData<List<TodoData>>

    init {
        repository = TodoRepository(TodoDatabase.getDatabase(application).todoDao())
        getAllData = repository.getAllData
    }

    fun insertData(todoData: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(todoData)
        }
    }

    fun updateData(todoData: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(todoData)
        }
    }

    fun deleteItem(todoData: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(todoData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchItems(searchQuery: String) : LiveData<List<TodoData>> {
        return repository.searchItems(searchQuery)
    }
}