package com.example.sqla

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listViewTasks: ListView
    private lateinit var taskDatabaseHelper: TaskDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sao chép cơ sở dữ liệu từ assets
        DatabaseCopier(this).copyDatabase()

        taskDatabaseHelper = TaskDatabaseHelper(this)

        listViewTasks = findViewById(R.id.listViewTasks)
        displayTasks()
    }

    private fun displayTasks() {
        val tasks = taskDatabaseHelper.getAllTasks()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            tasks.map { it.name }
        )

        listViewTasks.adapter = adapter
    }
    private fun addTask(task: Task) {
        taskDatabaseHelper.addTask(task)
        displayTasks()
    }

    private fun updateTask(task: Task) {
        taskDatabaseHelper.updateTask(task)
        displayTasks()
    }

    private fun deleteTask(taskId: Int) {
        taskDatabaseHelper.deleteTask(taskId)
        displayTasks()
    }
}
