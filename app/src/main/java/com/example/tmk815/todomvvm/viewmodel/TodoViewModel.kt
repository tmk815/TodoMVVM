package com.example.tmk815.todomvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tmk815.todomvvm.TodoRepository
import com.example.tmk815.todomvvm.db.entity.Todo

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository = TodoRepository(application)
    private val allTodos: LiveData<List<Todo>> = repository.findAll()

    fun insert(Todo: Todo) {
        repository.insert(Todo)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun findAll(): LiveData<List<Todo>> {
        return allTodos
    }
}