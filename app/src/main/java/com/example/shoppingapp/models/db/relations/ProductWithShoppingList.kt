package com.example.shoppingapp.models.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.shoppingapp.models.db.entities.ShoppingList
import com.example.shoppingapp.models.db.entities.Product

data class ProductWithShoppingList(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "productId",
        entityColumn = "shoppingListId",
        associateBy = Junction(ShoppingListWithProduct::class)
    )
    val shoppingLists: List<ShoppingList>
)