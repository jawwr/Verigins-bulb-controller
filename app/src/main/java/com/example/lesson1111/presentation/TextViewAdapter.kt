package com.example.lesson1111.presentation

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextViewAdapter : RecyclerView.Adapter<TextViewAdapter.TextViewHolder>() {
    private val list = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val context = parent.context
        val textView = TextView(context)
        return TextViewHolder(textView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.textView.text = list[position]
    }

    fun submit(list: List<String>) {
        with(this.list) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class TextViewHolder(
        val textView: TextView
    ) : RecyclerView.ViewHolder(textView)
}
