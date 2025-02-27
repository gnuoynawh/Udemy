package com.gnuoynawh.udemy.todo.data.repository

import androidx.lifecycle.LiveData
import com.gnuoynawh.udemy.todo.data.models.TodoData
import com.gnuoynawh.udemy.todo.data.TodoDao

class TodoRepository(private val todoDao: TodoDao) {

    val getAllData: LiveData<List<TodoData>> = todoDao.getAllTodoData()

    suspend fun insertData(todoData: TodoData) {
        todoDao.insertData(todoData = todoData)
    }

    suspend fun updateData(todoData: TodoData) {
        todoDao.updateData(todoData)
    }

    suspend fun deleteItem(todoData: TodoData) {
        todoDao.deleteItem(todoData)
    }

    suspend fun deleteAll() {
        todoDao.deleteAll()
    }

    fun searchItems(searchQuery: String) : LiveData<List<TodoData>> {
        return todoDao.searchItems(searchQuery)
    }

    fun sortByHighPriority() : LiveData<List<TodoData>> {
        return todoDao.sortByHighPriority()
    }

    fun sortByLowPriority() : LiveData<List<TodoData>> {
        return todoDao.sortByLowPriority()
    }
}