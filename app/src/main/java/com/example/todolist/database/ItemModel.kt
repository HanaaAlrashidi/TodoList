package com.example.todolist.database

import android.widget.CheckBox
import android.widget.TextView
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*

@Entity
data class ItemModel (
    var title: String,
    val date: String,
    var douDate: String,
    var description:String,
    var checkBox: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
        )

