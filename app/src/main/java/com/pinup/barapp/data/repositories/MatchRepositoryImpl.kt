package com.pinup.barapp.data.repositories

import com.pinup.barapp.data.remote.ApiService
import com.pinup.barapp.domain.MatchRepository
import com.pinup.barapp.domain.models.Match
import jakarta.inject.Inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MatchRepositoryImpl @Inject constructor(
    private val api: ApiService
) : MatchRepository {

    override suspend fun getMatchesBetween(start: LocalDate, end: LocalDate): List<Match> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return try {
            val response = api.getMatchesNext7Days(
                formatter.format(start),
                formatter.format(end)
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

    override suspend fun getUpcomingMatches(): List<Match> {
        val from = LocalDate.now()
        // fetch exactly the next 7 days including today
        val to = from.plusDays(6)
        return getMatchesBetween(from, to)
    }

    override suspend fun getRecentMatches(): List<Match> {
        val to = LocalDate.now().minusDays(1)
        val from = to.minusDays(6)
        return getMatchesBetween(from, to)
    }
}
