package com.tutorials.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    private lateinit var toDoList:ArrayList<ToDo>
    private lateinit var adapter:ToDoAdapter
    private lateinit var ds:DatabaseAssistant



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        copyDatabase()

        toolbar.title = "To Do"
        setSupportActionBar(toolbar)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this@MainActivity)

        ds = DatabaseAssistant(this@MainActivity)

        getAllToDo()

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddActivity::class.java))
        }

        fabHelp.setOnClickListener {
            alertGoster()
        }
    }

    fun alertGoster(){
        val view = LayoutInflater.from(this).inflate(R.layout.help_layout,null)
        val ad = AlertDialog.Builder(this)
        ad.setTitle("Help")
        ad.setView(view)

        ad.setNegativeButton("Ok") { dialogInterface, i->

        }
        ad.create().show()
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_arama_menu,menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("Send search results",query)
        callSearch(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("Results as letters",newText)
        callSearch(newText)
        return true
    }

    fun copyDatabase(){
        val copyHelper = DatabaseCopyHelper(this@MainActivity)
        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getAllToDo(){
        toDoList = WhatToDo().get(ds)
        adapter = ToDoAdapter(this@MainActivity,toDoList,ds)
        rv.adapter = adapter

    }

    fun callSearch(searchKeyword:String){
        toDoList = WhatToDo().search(ds, searchKeyword)
        adapter = ToDoAdapter(this@MainActivity,toDoList,ds)
        rv.adapter = adapter
    }

}