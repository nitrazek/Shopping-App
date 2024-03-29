package com.example.shoppingapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.models.db.relations.ShopWithAddress
import com.example.shoppingapp.models.repositories.FavouriteShopsRepository
import com.example.shoppingapp.ui.screens.favouriteshops.FavouriteShopsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteShopsViewModel @Inject constructor(
    private val repository: FavouriteShopsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavouriteShopsUiState())
    val uiState: StateFlow<FavouriteShopsUiState> = _uiState.asStateFlow()

    private fun execute(task: suspend () -> Unit) {
        viewModelScope.launch {
            task()
        }
    }

    fun fetchShops() {
        execute {
            val shops: List<ShopWithAddress> = repository.getShopsWithAddresses()
            _uiState.update { currentState -> currentState.copy(shops = shops) }
        }
    }
}