package com.tutorials.todoapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(var mContext:Context, var toDoList:ArrayList<ToDo>,var ds:DatabaseAssistant)
    : RecyclerView.Adapter<ToDoAdapter.CardDesignHolder>(){

        inner class CardDesignHolder(view:View):RecyclerView.ViewHolder(view){
            var line_card: CardView = view.findViewById(R.id.line_card)
            var line_text: TextView = view.findViewById(R.id.line_text)
            var line_image: ImageView = view.findViewById(R.id.line_image)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.card,parent,false)
        return CardDesignHolder(view)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        var work_to_do = toDoList[position]

        holder.line_text.text = work_to_do.work_to_do
        holder.line_image.setOnClickListener {
            Toast.makeText(mContext,"Job successfully deleted :)",Toast.LENGTH_LONG).show()
            WhatToDo().delete(ds, work_to_do.todo_id)
            toDoList = WhatToDo().get(ds)
            notifyDataSetChanged()
        }
        holder.line_card.setOnClickListener {
            val intent = Intent(mContext,DelayActivity::class.java)
            intent.putExtra("key", work_to_do)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }
}