package com.example.shoppingapp.models.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.shoppingapp.models.db.entities.Shop
import com.example.shoppingapp.models.db.entities.ShopAddress
import com.example.shoppingapp.models.db.relations.ShopWithAddress
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDao {
    @Transaction
    @Query("SELECT * FROM shops")
    suspend fun getShops(): List<ShopWithAddress>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShop(shop: Shop): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopAddress(address: ShopAddress): Long
}