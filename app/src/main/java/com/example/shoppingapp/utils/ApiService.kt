package com.example.shoppingapp.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenFoodFactsApi {
    @GET("api/v3/product/{barcode}")
    fun getProduct(@Path("barcode") barcode: String): Call<Map<String, Map<String, String>>>
}
class ApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://world.openfoodfacts.net")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(OpenFoodFactsApi::class.java)

    fun getProduct(barcode: String, callback: Callback<Map<String, Map<String, String>>>) {
        val call = api.getProduct(barcode)
        call.enqueue(callback)
    }
}