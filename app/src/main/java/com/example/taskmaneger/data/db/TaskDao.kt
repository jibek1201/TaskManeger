package com.example.taskmaneger.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.taskmaneger.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY title")
    fun getAll():List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task:Task)
}