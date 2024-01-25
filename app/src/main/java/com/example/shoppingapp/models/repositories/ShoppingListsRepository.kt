package com.example.shoppingapp.models.repositories
import com.example.shoppingapp.models.db.entities.ShoppingList
import javax.inject.Inject
import com.example.shoppingapp.models.db.dao.ShoppingListDao
import com.example.shoppingapp.models.db.entities.Product
import kotlinx.coroutines.flow.Flow

class ShoppingListsRepository @Inject constructor(
    private val shoppingListDao: ShoppingListDao
) {
    suspend fun getShoppingLists(): List<ShoppingList> {
        return shoppingListDao.getShoppingLists()
        //return listOf(ShoppingList (0,"Twoja stara",0), ShoppingList(1,"druga",0))
    }

    suspend fun addShoppingList(shoppingList: ShoppingList) {
        shoppingListDao.addShoppingList(shoppingList)
    }

    suspend fun deleteShoppingList(shoppingList: ShoppingList) {
        shoppingListDao.deleteShoppingListById(shoppingList.id)
    }
}