package com.example.shoppingapp.models.repositories

import com.example.shoppingapp.models.db.dao.ShopDao
import com.example.shoppingapp.models.db.entities.Shop
import com.example.shoppingapp.models.db.entities.ShopAddress
import com.example.shoppingapp.models.db.relations.ShopWithAddress
import com.example.shoppingapp.models.dto.ShopAddDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteShopsRepository @Inject constructor(
    private val shopDao: ShopDao
) {
    suspend fun getShopsWithAddresses(): List<ShopWithAddress> {
        return shopDao.getShops()
    }

    suspend fun addShop(shopAddDTO: ShopAddDTO) {
        val shop = Shop(name = shopAddDTO.name)
        val shopId: Long = shopDao.addShop(shop)

        val address = ShopAddress(
            shopId = shopId,
            city = shopAddDTO.city,
            street = shopAddDTO.street,
            postalCode = shopAddDTO.postalCode,
            latitude = shopAddDTO.latitude,
            longitude = shopAddDTO.longitude
        )
        shopDao.addShopAddress(address)
    }
}