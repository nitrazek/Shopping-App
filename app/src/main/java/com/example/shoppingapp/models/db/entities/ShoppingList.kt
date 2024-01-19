package com.example.shoppingapp.models.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingLists")
data class ShoppingList(
    @PrimaryKey val id: Long,
    val name: String,
    val shopId: Long
)