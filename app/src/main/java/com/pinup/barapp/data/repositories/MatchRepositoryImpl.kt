package com.pinup.barapp.data.repositories

import com.pinup.barapp.data.remote.ApiService
import com.pinup.barapp.data.remote.RetrofitClient
import com.pinup.barapp.domain.MatchRepository
import com.pinup.barapp.domain.models.Match
import jakarta.inject.Inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MatchRepositoryImpl @Inject constructor(
    private val api: ApiService
) : MatchRepository {

    override suspend fun getUpcomingMatches(): List<Match> {
        val from = LocalDate.of(2024, 5, 1)
        val to = LocalDate.of(2024, 5, 7)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return try {
            val response = api.getMatchesNext7Days(
                formatter.format(from),
                formatter.format(to),
                RetrofitClient.API_KEY
            )
            if (response.isSuccessful) {
                response.body()?.data?.map { it.toDomain() } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
