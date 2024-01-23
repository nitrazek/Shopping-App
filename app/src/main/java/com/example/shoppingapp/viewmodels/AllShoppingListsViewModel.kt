package com.example.shoppingapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.shoppingapp.models.repositories.ShoppingListsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.shoppingapp.models.db.entities.ShoppingList
import com.example.shoppingapp.ui.screens.allShopingLists.AllShoppingListsUiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AllShoppingListsViewModel @Inject constructor(
    private val repository: ShoppingListsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AllShoppingListsUiState())
    val uiState: StateFlow<AllShoppingListsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.shoppingLists.collect { shoppingLists ->
                _uiState.update { currentState -> currentState.copy(shoppingLists = shoppingLists) }
            }
        }
    }

   suspend fun addShoppingList(newShoppingList: ShoppingList) {
       viewModelScope.launch {
           repository.addShoppingList(newShoppingList)
       }
    }

   suspend fun deleteShoppingList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            repository.deleteShoppingList(shoppingList)
        }
    }
}