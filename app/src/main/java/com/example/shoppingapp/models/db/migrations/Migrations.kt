package com.example.shoppingapp.models.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS newProducts (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name,
                barcode
            )
        """.trimIndent())
        db.execSQL("""
            INSERT INTO newProducts (id, name, barcode)
            SELECT id, name, barcode FROM products
        """.trimIndent())
        db.execSQL("DROP TABLE products")
        db.execSQL("ALTER TABLE newProducts RENAME TO products")
    }
}