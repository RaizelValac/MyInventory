package com.example.myinventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val price: Double,
    val quantity: Int,
    val dateAdded: Long = System.currentTimeMillis(),
    val category: String = "General",
)