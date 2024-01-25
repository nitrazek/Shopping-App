package com.example.shoppingapp.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenFoodFactsApi {
    @GET("/api/v3/product/{barcode}.json")
    fun getProduct(@Path("barcode") barcode: String): Call<Map<String, Any>>
}
class ApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://world.openfoodfacts.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(OpenFoodFactsApi::class.java)

    fun getProduct(barcode: String, callback: Callback<Map<String, Any>>) {
        val call = api.getProduct(barcode)
        call.enqueue(callback)
    }
}