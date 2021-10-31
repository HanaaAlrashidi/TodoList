package com.example.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.ItemModel
import com.example.todolist.view.TodoViewModel

class TodoAdapter(val todoList: List<ItemModel>, val viewModel: TodoViewModel):
RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextview: TextView = view.findViewById(R.id.title_textView)
        val dateTextview: TextView = view.findViewById(R.id.date_textview)
        val taskCheckbox: CheckBox = view.findViewById(R.id.checkBox)
        val deleteButton: Button = view.findViewById(R.id.delete_Button)

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
        holder.dateTextview.text = toDo.date
        holder.taskCheckbox.isChecked = toDo.checkBox
        holder.deleteButton.setOnClickListener {


        }

        holder.itemView.setOnClickListener{
            viewModel.selectedItemMutableLiveData.postValue(toDo)
            it.findNavController().navigate(R.id.action_todoList_Fragment_to_item_Details_Fragment)
        }
        holder.taskCheckbox.setOnClickListener{
            toDo.checkBox = holder.taskCheckbox.isChecked
            viewModel.updateItem(toDo)
        }



    }

    override fun getItemCount(): Int {
        return todoList.size


    }
}