package com.pinup.barapp.data.repositories

import com.pinup.barapp.data.remote.ApiService
import com.pinup.barapp.data.remote.RetrofitClient
import com.pinup.barapp.domain.MatchRepository
import com.pinup.barapp.domain.models.Match
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val api: ApiService
) : MatchRepository {

    override suspend fun getUpcomingMatches(): List<Match> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val from = LocalDate.now()
        val to = from.plusDays(7)
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
