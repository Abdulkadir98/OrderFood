package com.example.admin.orderfood.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
@Entity(tableName = "food_table")
data class Food(@PrimaryKey(autoGenerate = true) val id: Int = 0, val title: String,
                val description: String, val price: Float, val cuisine: String)