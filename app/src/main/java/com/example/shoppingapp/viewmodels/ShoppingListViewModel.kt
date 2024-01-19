package com.example.shoppingapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.models.db.entities.Product
import com.example.shoppingapp.models.repositories.ProductWithQuantity
import com.example.shoppingapp.models.repositories.ShoppingListRepository
import com.example.shoppingapp.ui.screens.shoppingList.ShoppingListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ShoppingListUiState())
    val uiState: StateFlow<ShoppingListUiState> = _uiState.asStateFlow()

    fun fetchItems() {
        val shoppingItems: List<ProductWithQuantity> = repository.getProducts()
        _uiState.update { currentState ->
            currentState.copy( products = shoppingItems)
        }
    }

    fun addShoppingItem(newProduct: Product, quantity: Int) {
        repository.addProduct(newProduct,quantity)
        fetchItems()
    }
}