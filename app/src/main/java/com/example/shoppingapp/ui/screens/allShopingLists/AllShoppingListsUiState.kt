package com.example.shoppingapp.ui.screens.allShopingLists

import com.example.shoppingapp.models.db.entities.ShoppingList

data class AllShoppingListsUiState (
    val shoppingLists: List<ShoppingList> = listOf()
)