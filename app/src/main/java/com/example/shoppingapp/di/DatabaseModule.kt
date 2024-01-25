package com.example.shoppingapp.di

import android.content.Context
import androidx.room.Room
import com.example.shoppingapp.models.db.Database
import com.example.shoppingapp.models.db.MIGRATION_1_2
import com.example.shoppingapp.models.db.MIGRATION_2_3
import com.example.shoppingapp.models.db.dao.ShoppingListDao
import com.example.shoppingapp.models.db.dao.ProductDao
import com.example.shoppingapp.models.db.dao.ShopDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(
            appContext,
            Database::class.java,
            "database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideShoppingListDao(database: Database): ShoppingListDao {
        return database.shoppingListDao()
    }

    @Provides
    fun provideProductDao(database: Database): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideShopDao(database: Database): ShopDao {
        return database.shopDao()
    }
}