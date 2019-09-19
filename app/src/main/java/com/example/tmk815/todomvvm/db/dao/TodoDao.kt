package com.example.tmk815.todomvvm.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tmk815.todomvvm.db.entity.Todo

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createTodo(todo: Todo)

    @Insert
    fun insert(todo: Todo)

    @Query("SELECT * FROM todo_table")
    fun findAll(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE completed = :type")
    fun findSelect(type: Int): LiveData<List<Todo>>

    @Query("DELETE FROM todo_table")
    fun deleteAll()

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Query("DELETE FROM todo_table WHERE completed = 1")
    fun deleteCompleted()
}
