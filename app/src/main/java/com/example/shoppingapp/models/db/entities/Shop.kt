package com.example.shoppingapp.models.db.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "shops")
data class Shop(
    @PrimaryKey val id: Long,
    val name: String
)
