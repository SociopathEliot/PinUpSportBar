package com.pinup.barapp.data.repositories

import com.pinup.barapp.data.local.ReservationDao
import com.pinup.barapp.domain.models.Reservation
import jakarta.inject.Inject

class ReservationRepository @Inject constructor(
    private val dao: ReservationDao
) {
    suspend fun insert(reservation: Reservation) = dao.insert(reservation)
}
