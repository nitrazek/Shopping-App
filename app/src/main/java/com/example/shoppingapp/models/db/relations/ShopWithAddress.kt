package com.example.shoppingapp.models.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.shoppingapp.models.db.entities.Shop
import com.example.shoppingapp.models.db.entities.ShopAddress

data class ShopWithAddress(
    @Embedded val shop: Shop,
    @Relation(
        parentColumn = "id",
        entityColumn = "shopId"
    ) val address: ShopAddress
)