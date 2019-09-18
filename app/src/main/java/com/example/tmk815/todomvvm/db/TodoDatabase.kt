package com.example.tmk815.todomvvm.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tmk815.todomvvm.db.dao.TodoDao
import com.example.tmk815.todomvvm.db.entity.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var instance: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase? {
            if (instance == null) {
                synchronized(TodoDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java, "notes_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }


        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

}

class PopulateDbAsyncTask(db: TodoDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val noteDao = db?.todoDao()

    override fun doInBackground(vararg p0: Unit?) {
        noteDao?.insert(Todo( 0, "DBのアクセスは Room を使う"))
        noteDao?.insert(Todo( 0, "リストはRecyclerViewを使う"))
        noteDao?.insert(Todo( 0, "ViewModelとLiveDataを使う"))
    }

}