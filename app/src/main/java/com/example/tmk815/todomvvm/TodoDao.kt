package com.example.tmk815.todomvvm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createTodo(todo: Todo)

    @Query("SELECT * FROM Todo")
    fun findAll(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo WHERE completed = :type")
    fun findSelect(type: Int):LiveData<List<Todo>>

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}