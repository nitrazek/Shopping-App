package com.example.shoppingapp.models.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppingapp.models.db.entities.ShoppingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shoppingLists")
    suspend fun getShoppingLists(): List<ShoppingList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShoppingList(shoppingList: ShoppingList)

    @Query("DELETE FROM shoppinglists WHERE id = :shoppingListId")
    suspend fun deleteShoppingListById(shoppingListId: Long)
}