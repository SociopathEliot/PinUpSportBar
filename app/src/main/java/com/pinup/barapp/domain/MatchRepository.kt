package com.pinup.barapp.domain

import com.pinup.barapp.domain.models.Match
import java.time.Month

interface MatchRepository {
    suspend fun getUpcomingMatches(): List<Match>
    suspend fun getMatchesByMonth(month: Month): List<Match>
}