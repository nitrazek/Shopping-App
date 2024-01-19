package com.example.shoppingapp.ui.screens.favouriteshops

import com.example.shoppingapp.models.db.relations.ShopWithAddress

data class FavouriteShopsUiState(
    val shops: List<ShopWithAddress> = listOf()
)
