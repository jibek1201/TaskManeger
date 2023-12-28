package com.example.taskmaneger.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmaneger.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun taskDao():TaskDao
}