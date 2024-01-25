package com.example.shoppingapp.models.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppingapp.models.db.dao.ProductDao
import com.example.shoppingapp.models.db.dao.ShopDao
import com.example.shoppingapp.models.db.dao.ShoppingListDao
import com.example.shoppingapp.models.db.entities.Product
import com.example.shoppingapp.models.db.entities.Shop
import com.example.shoppingapp.models.db.entities.ShopAddress
import com.example.shoppingapp.models.db.entities.ShoppingList
import com.example.shoppingapp.models.db.relations.ShoppingListProductRef

@Database(
    entities = [
        ShoppingList::class,
        Product::class,
        Shop::class,
        ShopAddress::class,
        ShoppingListProductRef::class
    ],
    version = 1)
abstract class Database : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun productDao(): ProductDao
    abstract fun shopDao(): ShopDao
}