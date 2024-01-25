package com.example.shoppingapp.models.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS newProducts (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                barcode TEXT
            )
        """.trimIndent())
        db.execSQL("""
            INSERT INTO newProducts (id, name, barcode)
            SELECT id, name, barcode FROM products
        """.trimIndent())
        db.execSQL("DROP TABLE products")
        db.execSQL("ALTER TABLE newProducts RENAME TO products")

        db.execSQL("""
            CREATE TABLE IF NOT EXISTS newShops (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT
            )
        """.trimIndent())
        db.execSQL("""
            INSERT INTO newShops (id, name)
            SELECT id, name FROM shops
        """.trimIndent())
        db.execSQL("DROP TABLE shops")
        db.execSQL("ALTER TABLE newShops RENAME TO shops")

        db.execSQL("""
            CREATE TABLE IF NOT EXISTS newShopAddresses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                shopId INTEGER,
                city TEXT,
                street TEXT,
                postalCode TEXT,
                latitude REAL,
                longitude REAL,
                FOREIGN KEY (shopId) REFERENCES shops(id)
            )
        """.trimIndent())
        db.execSQL("""
            INSERT INTO newShopAddresses (id, shopId, city, street, postalCode, latitude, longitude)
            SELECT id, shopId, city, street, postalCode, latitude, longitude FROM shopAddresses
        """.trimIndent())
        db.execSQL("DROP TABLE shopAddresses")
        db.execSQL("ALTER TABLE newShopAddresses RENAME TO shopAddresses")

        db.execSQL("""
            CREATE TABLE IF NOT EXISTS newShoppingLists (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                shopId INTEGER,
                FOREIGN KEY (shopId) REFERENCES shops(id)
            )
        """.trimIndent())
        db.execSQL("""
            INSERT INTO newShoppingLists (id, name, shopId)
            SELECT id, name, shopId FROM shoppingLists
        """.trimIndent())
        db.execSQL("DROP TABLE shoppingLists")
        db.execSQL("ALTER TABLE newShoppingLists RENAME TO shoppingLists")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS newShopAddresses (
                city TEXT NULL,
                street TEXT NULL,
                postalCode TEXT NULL,
                latitude REAL NULL,
                id INTEGER PRIMARY KEY,
                shopId INTEGER NOT NULL,
                longitude REAL NULL
            )
        """.trimIndent())
        db.execSQL("""
            INSERT INTO newShopAddresses (id, shopId, city, street, postalCode, latitude, longitude)
            SELECT id, shopId, city, street, postalCode, latitude, longitude FROM shopAddresses
        """.trimIndent())
        db.execSQL("DROP TABLE shopAddresses")
        db.execSQL("ALTER TABLE newShopAddresses RENAME TO shopAddresses")
    }
}