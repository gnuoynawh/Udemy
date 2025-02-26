package com.gnuoynawh.udemy.todo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gnuoynawh.udemy.todo.data.models.TodoData

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodoData(): LiveData<List<TodoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(todoData: TodoData)

    @Update
    suspend fun updateData(todoData: TodoData)

    @Delete
    suspend fun deleteItem(todoData: TodoData)

    @Query("Delete from todo_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchItems(searchQuery: String) : LiveData<List<TodoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE " +
            "WHEN priority LIKE 'H%' THEN 1 " +
            "WHEN priority LIKE 'M%' THEN 2 " +
            "WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority() : LiveData<List<TodoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE " +
            "WHEN priority LIKE 'L%' THEN 1 " +
            "WHEN priority LIKE 'M%' THEN 2 " +
            "WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority() : LiveData<List<TodoData>>

}