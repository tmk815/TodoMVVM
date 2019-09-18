package com.example.tmk815.todomvvm

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoDao::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}