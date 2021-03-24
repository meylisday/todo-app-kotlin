package com.tutorials.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_delay.*

class DelayActivity : AppCompatActivity() {

    private lateinit var workToDo:ToDo
    private lateinit var ds:DatabaseAssistant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delay)

        toolbarDelay.title = "To Do Details"
        setSupportActionBar(toolbarDelay)
        ds = DatabaseAssistant(this@DelayActivity)

        workToDo = intent.getSerializableExtra("key") as ToDo
        editTextToDo.setText(workToDo.work_to_do)

        buttonUpdate.setOnClickListener {
            val work_to_do = editTextToDo.text.toString()

            update(workToDo.todo_id, work_to_do)

        }
    }

    fun update(todo_id:Int, work_to_do:String) {
        Log.e("Update to do","$todo_id - $work_to_do")

        WhatToDo().update(ds, todo_id, work_to_do)

        startActivity(Intent(this@DelayActivity, MainActivity::class.java))
    }
}