package com.tutorials.todoapp

import android.content.ContentValues

class WhatToDo {

    fun get(ds:DatabaseAssistant): ArrayList<ToDo>{
        val db = ds.writableDatabase
        val toDoList = ArrayList<ToDo>()
        val c = db.rawQuery("SELECT * FROM ToDoList",null)

        while(c.moveToNext()){
            val yapilacakIs = ToDo(c.getInt(c.getColumnIndex("todo_id"))
                ,c.getString(c.getColumnIndex("work_to_do")))
            toDoList.add(yapilacakIs)
        }
        return toDoList
    }

    fun search(ds:DatabaseAssistant, searchKeyword:String): ArrayList<ToDo>{
        val db = ds.writableDatabase
        val toDoList = ArrayList<ToDo>()
        val c = db.rawQuery("SELECT * FROM ToDoList WHERE work_to_do like '%$searchKeyword%'",null)

        while(c.moveToNext()){
            val work_to_do = ToDo(c.getInt(c.getColumnIndex("todo_id"))
                ,c.getString(c.getColumnIndex("work_to_do")))
            toDoList.add(work_to_do)
        }
        return toDoList
    }

    fun delete(ds:DatabaseAssistant, todo_id:Int) {
        val db = ds.writableDatabase
        db.delete("ToDoList","todo_id=?",arrayOf(todo_id.toString()))
        db.close()
    }

    fun insert(ds:DatabaseAssistant, work_to_do:String) {
        val db = ds.writableDatabase
        val values = ContentValues()
        values.put("work_to_do", work_to_do)

        db.insertOrThrow("ToDoList",null,values)

        db.close()
    }

    fun update(ds:DatabaseAssistant, todo_id:Int, work_to_do:String) {
        val db = ds.writableDatabase
        val values = ContentValues()
        values.put("work_to_do", work_to_do)

        db.update("ToDoList",values,"todo_id=?", arrayOf(todo_id.toString()))

        db.close()
    }
}

