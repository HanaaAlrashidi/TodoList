package com.example.todolist.repository

import android.content.Context
import androidx.room.Room
import com.example.todolist.database.ItemModel
import com.example.todolist.database.TodoDatabase
import java.lang.Exception

private const val DATABASE_NAME = "todo-database"
class TodoRepository(context: Context) {

    private val database: TodoDatabase =
        Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    private val todoDao = database.todoDao()

    fun getItems() = todoDao.getItems()

    suspend fun addItem(itemModel: ItemModel) = todoDao.addItem(itemModel)
    suspend fun updateItem(itemModel: ItemModel) = todoDao.updateItem(itemModel)
    suspend fun deleteItem(itemModel: ItemModel) = todoDao.deleteItem(itemModel)

    companion object {
        private var instance: TodoRepository? = null

        fun init(context: Context){
            if (instance == null)
                instance = TodoRepository(context)
        }

        fun get(): TodoRepository{
            return instance ?: throw Exception("Todo Repository must be initialized")
        }
    }


}