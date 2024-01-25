package com.example.shoppingapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.shoppingapp.models.repositories.ShoppingListsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.shoppingapp.models.db.entities.ShoppingList
import com.example.shoppingapp.models.db.relations.ShopWithAddress
import com.example.shoppingapp.ui.screens.allShopingLists.AllShoppingListsUiState
import kotlinx.coroutines.async
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
            var shoppingLists = repository.getShoppingLists()
            if(shoppingLists.isNotEmpty()) return@launch
            repository.addShoppingList(ShoppingList(0,"Twoja stara",0))
            repository.addShoppingList(ShoppingList(1,"druga",0))
            shoppingLists = repository.getShoppingLists()
            _uiState.update { currentState -> currentState.copy(shoppingLists = shoppingLists) }
        }
    }

    fun fetchShoppingLists() {
        viewModelScope.launch {
            val shoppingLists = repository.getShoppingLists()
            _uiState.update { currentState -> currentState.copy(shoppingLists = shoppingLists) }
        }
    }

    fun addShoppingList(newShoppingList: ShoppingList) {
        viewModelScope.launch {
            repository.addShoppingList(newShoppingList)
            val shoppingLists = repository.getShoppingLists()
            _uiState.update { currentState -> currentState.copy(shoppingLists = shoppingLists) }
       }
   }

   fun deleteShoppingList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            repository.deleteShoppingList(shoppingList)
            val shoppingLists = repository.getShoppingLists()
            _uiState.update { currentState -> currentState.copy(shoppingLists = shoppingLists) }
        }
    }
}