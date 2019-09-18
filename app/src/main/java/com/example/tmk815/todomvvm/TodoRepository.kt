package com.example.tmk815.todomvvm

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.tmk815.todomvvm.db.TodoDatabase
import com.example.tmk815.todomvvm.db.dao.TodoDao
import com.example.tmk815.todomvvm.db.entity.Todo

class TodoRepository(application: Application){
    private var todoDao: TodoDao

    private var allTodos: LiveData<List<Todo>>

    init {
        val database: TodoDatabase = TodoDatabase.getInstance(application.applicationContext)!!
        todoDao = database.todoDao()
        allTodos = todoDao.findAll()
    }

    fun insert(todo: Todo) {
        val insertTodoAsyncTask = InsertTodoAsyncTask(todoDao).execute(todo)
    }

    fun deleteCompleted() {
        val DeleteCompletedTodosAsyncTask = DeleteCompletedTodosAsyncTask(
            todoDao
        ).execute()
    }

    fun deleteAll() {
        val DeleteAllAsyncTask = DeleteAllAsyncTask(
            todoDao
        ).execute()
    }

    fun findAll(): LiveData<List<Todo>> {
        return allTodos
    }

    private class InsertTodoAsyncTask(todoDao: TodoDao) : AsyncTask<Todo, Unit, Unit>() {
        val todoDao = todoDao

        override fun doInBackground(vararg p0: Todo?) {
            todoDao.insert(p0[0]!!)
        }
    }


    private class DeleteCompletedTodosAsyncTask(val todoDao: TodoDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            todoDao.deleteCompleted()
        }
    }

    private class DeleteAllAsyncTask(val todoDao: TodoDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            todoDao.deleteAll()
        }
    }
}