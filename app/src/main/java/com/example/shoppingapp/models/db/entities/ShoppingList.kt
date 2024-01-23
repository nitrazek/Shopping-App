package com.example.shoppingapp.models.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingLists")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val shopId: Long = 0
)