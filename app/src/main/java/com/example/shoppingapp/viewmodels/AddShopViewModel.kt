package com.example.shoppingapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.ui.screens.addShop.AddShopUiState
import com.example.shoppingapp.ui.screens.favouriteshops.FavouriteShopsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddShopViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AddShopUiState())
    val uiState: StateFlow<AddShopUiState> = _uiState.asStateFlow()

    fun setName(name: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(nameText = name)
        }
    }
    fun setLatitude(latitude: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(latitudeText = latitude)
        }
    }
    fun setLongitude(longitude: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(longitudeText = longitude)
        }
    }
    fun setCity(city: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(cityText = city)
        }
    }
    fun setStreet(street: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(streetText = street)
        }
    }
    fun setPostalCode(postalCode: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(postalCodeText = postalCode)
        }
    }

    fun addShop() {

    }
}