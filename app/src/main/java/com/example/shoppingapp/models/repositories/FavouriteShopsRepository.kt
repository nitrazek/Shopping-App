package com.example.shoppingapp.models.repositories

import com.example.shoppingapp.models.Shop
import javax.inject.Inject

class FavouriteShopsRepository @Inject constructor() {
    private val shops: List<Shop> = listOf(
        Shop(
            name = "sklep1",
            city = "Białystok",
            street = "Szkolna 17",
            postalCode = "15-123"
        ),
        Shop("sklep2"),
        Shop("sklep3"),
    )

    fun getShops(): List<Shop> {
        return shops
    }
}