package com.pinup.barapp.ui.fragments.tablebooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinup.barapp.data.repositories.ReservationRepository
import com.pinup.barapp.domain.models.Reservation
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val repository: ReservationRepository
) : ViewModel() {
    fun addReservation(reservation: Reservation) {
        viewModelScope.launch { repository.insert(reservation) }
    }
}
