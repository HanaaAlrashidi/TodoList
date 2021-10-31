package com.example.todolist.view

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ItemModel
import com.example.todolist.repository.TodoRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class TodoViewModel: ViewModel() {

    private val todoRepository = TodoRepository.get()

    var todoItems = todoRepository.getItems()

    var selectedItemMutableLiveData = MutableLiveData<ItemModel>()


    fun addItem(title: String,
                 douDate: String,
                 description:String,
                 checkBox: Boolean) {

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        viewModelScope.launch {
            todoRepository.addItem(ItemModel(title, currentDate, douDate, description, checkBox))
        }
    }

        fun updateItem(itemModel: ItemModel) {
            viewModelScope.launch {
                todoRepository.updateItem(itemModel)
            }
        }


            fun deleteItem(itemModel: ItemModel) {
                viewModelScope.launch {
                    todoRepository.deleteItem(itemModel)
                }
            }
        }