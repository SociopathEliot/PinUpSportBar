package com.pinup.barapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinup.barapp.data.repositories.CartRepository
import com.pinup.barapp.data.repositories.ReservationRepository
import com.pinup.barapp.domain.models.Reservation
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