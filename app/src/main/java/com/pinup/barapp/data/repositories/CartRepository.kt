package com.pinup.barapp.data.repositories

import com.pinup.barapp.data.remote.local.CartDao
import com.pinup.barapp.domain.models.CartItem
import kotlinx.coroutines.flow.Flow

class CartRepository(private val dao: CartDao) {

    suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }


    fun getCartItems(): Flow<List<CartItem>> = dao.getAll()

    fun getTotalQuantity(): Flow<Int> = dao.getTotalQuantity()

    fun getTotalPrice(): Flow<Double> = dao.getTotalPrice()

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


    suspend fun increaseQuantity(item: CartItem) {
        dao.increaseQuantity(item.id)
    }

    suspend fun decreaseQuantity(item: CartItem) {
        dao.decreaseQuantity(item.id)
    }

    fun getAll() = dao.getAll()


}