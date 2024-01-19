package com.example.shoppingapp.models.repositories

import com.example.shoppingapp.models.db.entities.Product
import javax.inject.Inject

data class ProductWithQuantity(
    val product: Product,
    val quantity: Int
)

class ShoppingListRepository @Inject constructor() {
    private val shoppingProducts: MutableList<ProductWithQuantity> = mutableListOf(
        ProductWithQuantity (Product(1,"mleko","7777"),1),
        ProductWithQuantity ( Product(2,"wino","9999"),4),
        ProductWithQuantity (Product(3,"woda","3636366363"),10)
    )

    fun getProducts(): List<ProductWithQuantity> {
        return shoppingProducts;
    }

    fun addProduct(shoppingProduct: Product, quantity: Int) {
        val productWithQuantity = ProductWithQuantity(shoppingProduct, quantity)
        shoppingProducts+= productWithQuantity
    }

}