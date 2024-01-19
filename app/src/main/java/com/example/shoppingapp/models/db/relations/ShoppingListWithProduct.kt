package com.example.shoppingapp.models.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.shoppingapp.models.db.entities.Product
import com.example.shoppingapp.models.db.entities.ShoppingList
import com.example.shoppingapp.models.db.relations.ProductWithShoppingList

data class ShoppingListWithProduct(
    @Embedded val shoppingList: ShoppingList,
    @Relation(
        parentColumn = "shoppingListId",
        entityColumn = "productId",
        associateBy = Junction(ProductWithShoppingList::class)
    )
    val products: List<Product>
)