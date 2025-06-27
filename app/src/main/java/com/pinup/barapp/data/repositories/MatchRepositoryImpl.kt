package com.pinup.barapp.data.repositories

import com.pinup.barapp.data.remote.apiservices.ApiService
import com.pinup.barapp.data.remote.RetrofitClient
import com.pinup.barapp.domain.MatchRepository
import com.pinup.barapp.domain.models.Match
import jakarta.inject.Inject
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter


class MatchRepositoryImpl @Inject constructor(
    private val api: ApiService
) : MatchRepository {

    override suspend fun getUpcomingMatches(): List<Match> {
        val from = LocalDate.now()
        val to = from.withDayOfMonth(from.lengthOfMonth())
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

    override suspend fun getMatchesByMonth(month: Month): List<Match> {
        val current = LocalDate.now()
        val year = if (month.value < current.month.value) current.year + 1 else current.year
        val from = LocalDate.of(year, month, 1)
        val to = from.withDayOfMonth(from.lengthOfMonth())
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
