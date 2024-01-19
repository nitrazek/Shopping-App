package com.example.shoppingapp.ui.screens.addShop

data class AddShopUiState(
    var nameText: String = "",
    var latitudeText: String = "",
    var longitudeText: String = "",
    var cityText: String = "",
    var streetText: String = "",
    var postalCodeText: String = "",

    var isFormValid: Boolean = true
)