package com.pinup.barapp.domain.models

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageRes: Int
)
