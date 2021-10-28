package com.example.todolist.database

import android.widget.CheckBox
import android.widget.TextView
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*

@Entity
data class ItemModel (
    val title: String,
    val date: String,
    val douDate: String,
    val description:String,
    var checkBox: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
        )

