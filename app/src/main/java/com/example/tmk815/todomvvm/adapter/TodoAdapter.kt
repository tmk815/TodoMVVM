package com.example.tmk815.todomvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmk815.todomvvm.R
import com.example.tmk815.todomvvm.db.entity.Todo
import com.example.tmk815.todomvvm.viewmodel.TodoViewModel


class TodoAdapter(private val viewModel: TodoViewModel) :
    RecyclerView.Adapter<TodoAdapter.TodoHolder>() {
    private var todos: List<Todo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)

        return TodoHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val currentTodo = todos[position]
        holder.checkBox.isChecked = currentTodo.completed != 0
        holder.todoText.setText(currentTodo.todoText, TextView.BufferType.NORMAL)

        holder.checkBox.setOnClickListener {
            currentTodo.completed = if (holder.checkBox.isChecked) 1 else 0
            viewModel.update(currentTodo)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun setTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        val currentTodo = todos[position]
        viewModel.delete(currentTodo)
        notifyItemRemoved(position)
    }

    class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
        var todoText: EditText = itemView.findViewById(R.id.todo_text)
    }
}
