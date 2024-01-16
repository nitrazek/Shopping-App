package com.example.shoppingapp.models

import android.location.Address

data class Shop(
    val name: String,
    val city: String? = null,
    val street: String? = null,
    val postalCode: String? = null
) {
    fun getAddressText(): String {
        val stringBuilder = StringBuilder("")
        if(!city.isNullOrEmpty()) stringBuilder.append(city)
        if(stringBuilder.isNotEmpty()) stringBuilder.append(", ")
        if(!street.isNullOrEmpty()) stringBuilder.append(street)
        if(stringBuilder.isNotEmpty()) stringBuilder.append(" ")
        if(!postalCode.isNullOrEmpty()) stringBuilder.append(postalCode)
        return stringBuilder.toString()
    }
}
