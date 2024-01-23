package com.example.shoppingapp.models.dto

data class ShopAddDTO(
    val name: String,
    val city: String,
    val street: String,
    val postalCode: String,
    val latitude: Double,
    val longitude: Double
)
