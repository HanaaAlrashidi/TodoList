package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TodoDao {

    @Insert
    suspend fun addItem(itemModel: ItemModel)

    @Query("SELECT * FROM ITEMMODEL")
    fun getItems(): LiveData<List<ItemModel>>

    @Update
    suspend fun updateItem(itemModel: ItemModel)

    @Delete
    suspend fun deleteItem(itemModel: ItemModel)
}