package com.tutorials.todoapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseAssistant(context: Context)
    :SQLiteOpenHelper(context,"todoapp.sqlite",null,1){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS \"ToDoList\" (\n" +
                "\t\"todo_id\"\tINTEGER,\n" +
                "\t\"work_to_do\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"todo_id\" AUTOINCREMENT)\n" +
                ")")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ToDoList")
        onCreate(db)
    }
}
