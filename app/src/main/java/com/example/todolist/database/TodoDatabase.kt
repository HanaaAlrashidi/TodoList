package com.example.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import java.util.*

@Database(entities = [ItemModel::class], version = 1 )
abstract class TodoDatabase: RoomDatabase() {

    
    abstract fun todoDao(): TodoDao

}