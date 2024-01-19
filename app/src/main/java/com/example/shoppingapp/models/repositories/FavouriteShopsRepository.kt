package com.example.shoppingapp.models.repositories

import com.example.shoppingapp.models.db.dao.ShopDao
import com.example.shoppingapp.models.db.relations.ShopWithAddress
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteShopsRepository @Inject constructor(
    private val shopDao: ShopDao
) {
    val shops: Flow<List<ShopWithAddress>> = shopDao.getShops()
}