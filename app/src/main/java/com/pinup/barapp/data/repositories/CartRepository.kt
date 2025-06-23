package com.pinup.barapp.data.repositories

import com.pinup.barapp.data.local.CartDao
import com.pinup.barapp.domain.models.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepository @Inject constructor(private val dao: CartDao) {

    suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }


    fun getCartItems(): Flow<List<CartItem>> = dao.getAll()

    fun getTotalQuantity(): Flow<Int> =
        dao.getTotalQuantity().map { it ?: 0 }

    fun getTotalPrice(): Flow<Double> =
        dao.getTotalPrice().map { it ?: 0.0 }

    suspend fun insert(item: CartItem) {
        val existing = dao.getItemById(item.id)
        if (existing != null) {
            dao.increaseQuantity(item.id)
        } else {
            dao.insert(item)
        }
    }

    suspend fun update(item: CartItem) = dao.update(item)

    suspend fun delete(item: CartItem) = dao.delete(item)

    suspend fun clearCart() = dao.clearCart()
    suspend fun deleteAll(items: List<CartItem>) = dao.deleteAll(items)

    suspend fun increaseQuantity(item: CartItem) {
        dao.increaseQuantity(item.id)
    }

    suspend fun decreaseQuantity(item: CartItem) {
        dao.decreaseQuantity(item.id)
    }

}