package com.pinup.barapp.ui.fragments.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinup.barapp.data.repositories.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class QrFragmentViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {
    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }
}