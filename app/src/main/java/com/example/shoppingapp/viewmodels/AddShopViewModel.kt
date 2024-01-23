package com.example.shoppingapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.models.dto.ShopAddDTO
import com.example.shoppingapp.models.repositories.FavouriteShopsRepository
import com.example.shoppingapp.ui.screens.addShop.AddShopUiState
import com.example.shoppingapp.ui.screens.favouriteshops.FavouriteShopsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddShopViewModel @Inject constructor(
    private val repository: FavouriteShopsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddShopUiState())
    val uiState: StateFlow<AddShopUiState> = _uiState.asStateFlow()

    fun setName(name: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(nameText = name, isNameValid = isValid)
        }
    }
    fun setLatitude(latitude: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(latitudeText = latitude, isLatitudeValid = isValid)
        }
    }
    fun setLongitude(longitude: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(longitudeText = longitude, isLongitudeValid = isValid)
        }
    }
    fun setCity(city: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(cityText = city, isCityValid = isValid)
        }
    }
    fun setStreet(street: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(streetText = street, isStreetValid = isValid)
        }
    }
    fun setPostalCode(postalCode: String, isValid: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(postalCodeText = postalCode, isPostalCodeValid = isValid)
        }
    }

    fun addShop() {
        if(!uiState.value.isFormValid()) return
        _uiState.update { currentState -> currentState.copy(isLoading = true) }
        viewModelScope.launch {
            repository.addShop(ShopAddDTO(
                name = uiState.value.nameText,
                city = uiState.value.cityText,
                street = uiState.value.streetText,
                postalCode = uiState.value.streetText,
                latitude = uiState.value.latitudeText.toDouble(),
                longitude = uiState.value.longitudeText.toDouble()
            ))
        }
        _uiState.update { currentState -> currentState.copy(isLoading = false) }
    }
}