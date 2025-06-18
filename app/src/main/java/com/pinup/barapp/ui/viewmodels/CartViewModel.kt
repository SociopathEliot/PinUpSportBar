package com.pinup.barapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pinup.barapp.data.remote.local.AppDatabase
import com.pinup.barapp.data.repositories.CartRepository
import com.pinup.barapp.domain.models.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CartRepository
    val totalQuantity: LiveData<Int>
    val totalPrice: LiveData<Double>
    val cartItems: LiveData<List<CartItem>>

    init {
        val dao = AppDatabase.getDatabase(application).cartDao()
        repository = CartRepository(dao)
        totalQuantity = repository.getTotalQuantity().asLiveData()
        totalPrice = repository.getTotalPrice().asLiveData()
        cartItems = repository.getAll().asLiveData()
    }

    fun addToCart(item: CartItem) {
        viewModelScope.launch {
            repository.insert(item)
        }
    }

    fun increaseQuantity(item: CartItem) {
        viewModelScope.launch {
            repository.increaseQuantity(item)
        }
    }

    fun decreaseQuantity(item: CartItem) {
        viewModelScope.launch {
            repository.decreaseQuantity(item)
        }
    }

    fun removeFromCart(item: CartItem) {
        viewModelScope.launch {
            repository.deleteById(item.id)
        }
    }
} 