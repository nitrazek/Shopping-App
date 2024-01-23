package com.example.shoppingapp.ui.screens.addShop

data class AddShopUiState(
    var isLoading: Boolean = false,

    var nameText: String = "",
    var isNameValid: Boolean = true,

    var latitudeText: String = "",
    var isLatitudeValid: Boolean = true,

    var longitudeText: String = "",
    var isLongitudeValid: Boolean = true,

    var cityText: String = "",
    var isCityValid: Boolean = true,

    var streetText: String = "",
    var isStreetValid: Boolean = true,

    var postalCodeText: String = "",
    var isPostalCodeValid: Boolean = true
) {
    fun isFormValid(): Boolean {
        return isNameValid &&
                isLatitudeValid &&
                isLongitudeValid &&
                isCityValid &&
                isStreetValid &&
                isPostalCodeValid
    }
}