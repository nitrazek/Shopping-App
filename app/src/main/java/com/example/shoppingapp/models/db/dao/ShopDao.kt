package com.example.shoppingapp.models.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.shoppingapp.models.db.relations.ShopWithAddress
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDao {
    @Transaction
    @Query("SELECT * FROM shops")
    fun getShops(): Flow<List<ShopWithAddress>>
}