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

    private val todoDao: TodoDao = TodoDatabase.getDatabase(application).todoDao()
    private val repository: TodoRepository

    private val getAllData: LiveData<List<TodoData>>

    init {
        repository = TodoRepository(todoDao)
        getAllData = repository.getAllData
    }

    fun insertData(todoData: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(todoData)
        }
    }
}