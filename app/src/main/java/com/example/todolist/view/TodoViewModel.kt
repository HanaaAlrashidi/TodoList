package com.example.todolist.view

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ItemModel
import com.example.todolist.repository.TodoRepository
import kotlinx.coroutines.launch
import java.util.*

class TodoViewModel: ViewModel() {

    private val todoRepository = TodoRepository.get()


    fun addItem(title: String,
                 date: String,
                 douDate: String,
                 description:String,
                 checkBox: Boolean){
        viewModelScope.launch {
            todoRepository.addItem(ItemModel(title,date,douDate,description,checkBox))
        }

        fun updateItem(itemModel: ItemModel){
            viewModelScope.launch {
                todoRepository.updateItem(itemModel)
            }

            fun deleteItem(itemModel: ItemModel){
                viewModelScope.launch {
                    todoRepository.deleteItem(itemModel)
                }

            }
        }
    }
}