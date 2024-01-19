package com.example.shoppingapp.models.db.relations

import androidx.room.Entity

@Entity(primaryKeys = ["shoppingListId", "productId"])
data class ShoppingListProductRef(
    val shoppingListId: Long,
    val productId: Long,
    val count: Int
)
