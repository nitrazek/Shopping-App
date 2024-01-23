package com.example.shoppingapp.models.repositories
import com.example.shoppingapp.models.db.entities.ShoppingList
import javax.inject.Inject
import com.example.shoppingapp.models.db.dao.ShoppingListDao
import kotlinx.coroutines.flow.Flow

class ShoppingListsRepository @Inject constructor(
    private val shoppingListDao: ShoppingListDao
) {
    val shoppingLists: Flow<List<ShoppingList>> = shoppingListDao.getShoppingLists()

    suspend fun addShoppingList(shoppingList: ShoppingList) {
        shoppingListDao.addShoppingList(shoppingList)
    }

    suspend fun deleteShoppingList(shoppingList: ShoppingList) {
        shoppingListDao.deleteShoppingListById(shoppingList.id)
    }
}