package com.example.tmk815.todomvvm.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmk815.todomvvm.R
import com.example.tmk815.todomvvm.adapter.TodoAdapter
import com.example.tmk815.todomvvm.db.entity.Todo
import com.example.tmk815.todomvvm.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val ADD_TODO_REQUEST = 1
    private lateinit var todoViewModel: TodoViewModel
    private val adapter = TodoAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = recycler_view

        button_add_todo.setOnClickListener {
            startActivityForResult(
                Intent(this, AddTodoActivity::class.java),
                ADD_TODO_REQUEST
            )
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        todoViewModel.findAll().observe(this,
            Observer<List<Todo>> {
                if (it != null) {
                    adapter.setTodos(it)
                }
            })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_todos -> {
                todoViewModel.deleteAll()
                Toast.makeText(this, "All todos deleted!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_TODO_REQUEST && resultCode == Activity.RESULT_OK) {
            val newTodo = Todo(
                0,
                data!!.getStringExtra(AddTodoActivity.TODO)
            )
            todoViewModel.insert(newTodo)

            Toast.makeText(this, "Todo saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Todo not saved!", Toast.LENGTH_SHORT).show()
        }


    }
}
