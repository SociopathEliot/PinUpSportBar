package com.pinup.barapp.data.remote.local

import androidx.room.*
import com.pinup.barapp.domain.models.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: CartItem)

    @Query("SELECT * FROM cart_items WHERE id = :id")
    suspend fun getItemById(id: Int): CartItem?

    @Query("DELETE FROM cart_items WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Update
    suspend fun update(item: CartItem)

    @Delete
    suspend fun delete(item: CartItem)

    @Query("UPDATE cart_items SET quantity = quantity + 1 WHERE id = :id")
    suspend fun increaseQuantity(id: Int)

    @Query("UPDATE cart_items SET quantity = quantity - 1 WHERE id = :id AND quantity > 1")
    suspend fun decreaseQuantity(id: Int)


    @Query("DELETE FROM cart_items")
    suspend fun clearCart()


    @Query("SELECT * FROM cart_items")
    fun getAll(): Flow<List<CartItem>>

    @Query("SELECT SUM(quantity) FROM cart_items")
    fun getTotalQuantity(): Flow<Int?>

    @Query("SELECT SUM(price * quantity) FROM cart_items")
    fun getTotalPrice(): Flow<Double?>
}
