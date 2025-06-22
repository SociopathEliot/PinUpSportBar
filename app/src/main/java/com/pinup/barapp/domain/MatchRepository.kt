package com.pinup.barapp.domain

import com.pinup.barapp.domain.models.Match
import java.time.LocalDate

interface MatchRepository {
    suspend fun getUpcomingMatches(): List<Match>

    suspend fun getMatchesBetween(start: LocalDate, end: LocalDate): List<Match>

    /**
     * Return matches from the last seven days (excluding today).
     */
    suspend fun getRecentMatches(): List<Match>
}
