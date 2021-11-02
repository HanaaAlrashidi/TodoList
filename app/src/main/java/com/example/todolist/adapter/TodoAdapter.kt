package com.example.todolist.adapter

import android.graphics.Paint
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.ItemModel
import com.example.todolist.view.TodoViewModel
import java.util.*

class TodoAdapter(val todoList: List<ItemModel>, val viewModel: TodoViewModel) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextview: TextView = view.findViewById(R.id.title_textView)
        val dateTextview: TextView = view.findViewById(R.id.date_textview)
        val taskCheckbox: CheckBox = view.findViewById(R.id.checkBox)
        val deleteButton: ImageButton = view.findViewById(R.id.delete_Button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val toDo = todoList[position]

        holder.titleTextview.text = toDo.title
        holder.dateTextview.text = toDo.douDate
        holder.taskCheckbox.isChecked = toDo.checkBox
        holder.deleteButton.setOnClickListener {
            viewModel.deleteItem(toDo)
        }

        holder.itemView.setOnClickListener {
            viewModel.selectedItemMutableLiveData.postValue(toDo)
            it.findNavController().navigate(R.id.action_todoList_Fragment_to_item_Details_Fragment)
        }

        if (toDo.douDate.isNotEmpty()) {

            var currentDate = Date()
            val format = SimpleDateFormat("yyyy/MM/dd")
            val deadline = format.parse(toDo.douDate)


            Log.d("toDo.douDate", toDo.douDate.toString())
            Log.d("currentDate", currentDate.toString())
            Log.d("deadline", deadline.toString())

            if (currentDate < deadline) {
                holder.titleTextview.setPaintFlags(0)
            } else {
                holder.titleTextview.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }


        holder.taskCheckbox.setOnClickListener {
            toDo.checkBox = holder.taskCheckbox.isChecked
            viewModel.updateItem(toDo)

            if (holder.taskCheckbox.isChecked) {
                holder.titleTextview.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
            } else {
                holder.titleTextview.setPaintFlags(0)
            }
        }


    }

    override fun getItemCount(): Int {
        return todoList.size


    }
}