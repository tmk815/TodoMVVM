package com.example.tmk815.todomvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo constructor(
    @PrimaryKey(autoGenerate = true) val _id: Int,
    val completed: Int,
    val todoText: String
)