package com.example.shoppingapp.models.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopAddresses")
data class ShopAddress(
    @PrimaryKey val id: Long,
    val shopId: Long,
    val city: String,
    val street: String,
    val postalCode: String,
    val latitude: Double,
    val longitude: Double
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