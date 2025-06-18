package com.pinup.barapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val imageRes: Int,
    val quantity: Int = 1
)
