package com.example.shoppingapp.viewmodels

import android.Manifest
import android.app.Application
import android.content.ContentProviderClient
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.shoppingapp.models.dto.ShopAddDTO
import com.example.shoppingapp.models.repositories.FavouriteShopsRepository
import com.example.shoppingapp.ui.screens.addShop.AddShopUiState
import com.example.shoppingapp.ui.screens.favouriteshops.FavouriteShopsUiState
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddShopViewModel @Inject constructor(
    application: Application,
    private val repository: FavouriteShopsRepository
) : AndroidViewModel(application) {
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

    fun addShop(): Either<Unit, Unit> {
        if(!uiState.value.isFormValid()) return Unit.left()
        _uiState.update { currentState -> currentState.copy(isLoading = true) }

        viewModelScope.launch {
            repository.addShop(ShopAddDTO(
                name = uiState.value.nameText,
                city = uiState.value.cityText.ifEmpty { null },
                street = uiState.value.streetText.ifEmpty { null },
                postalCode = uiState.value.postalCodeText.ifEmpty { null },
                latitude = uiState.value.latitudeText.toDoubleOrNull(),
                longitude = uiState.value.longitudeText.toDoubleOrNull()
            ))
        }
        _uiState.update { currentState -> currentState.copy(isLoading = false) }
        return Unit.right()
    }

    fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                getApplication<Application>().applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getApplication<Application>().applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) { return }

        _uiState.update { currentState -> currentState.copy(isLoading = true) }
        val context = getApplication<Application>().applicationContext
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                setLatitude(location.latitude.toString(), true)
                setLongitude(location.longitude.toString(), true)
                locationManager.removeUpdates(this)
                _uiState.update { currentState -> currentState.copy(isLoading = false) }
            }
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener)
    }
}