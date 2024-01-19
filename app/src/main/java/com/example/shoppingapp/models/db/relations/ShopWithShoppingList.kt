package com.example.shoppingapp.models.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.shoppingapp.models.db.entities.Shop
import com.example.shoppingapp.models.db.entities.ShoppingList

data class ShopWithShoppingList(
    @Embedded val shop: Shop,
    @Relation(
        parentColumn = "id",
        entityColumn = "shopId"
    ) val shoppingLists: List<ShoppingList>
)