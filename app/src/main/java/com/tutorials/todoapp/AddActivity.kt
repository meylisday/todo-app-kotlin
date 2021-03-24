package com.tutorials.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    private lateinit var ds:DatabaseAssistant
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        toolbarAdd.title = "To Do Create"
        setSupportActionBar(toolbarAdd)

        ds = DatabaseAssistant(this@AddActivity)

        buttonAdd.setOnClickListener {
            val work = editTextToDo.text.toString()

            write(work)

        }
    }

    fun write(work:String){
        Log.e("What should be done?","$work")

        WhatToDo().insert(ds,work)
        startActivity(Intent(this@AddActivity, MainActivity::class.java))
    }
}