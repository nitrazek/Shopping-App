package com.example.shoppingapp.ui.screens.shoppingList
import com.example.shoppingapp.models.repositories.ProductWithQuantity

data class ShoppingListUiState(
    val products:List<ProductWithQuantity> = listOf()
)


