package com.pinup.barapp.domain

import com.pinup.barapp.domain.models.Match

interface MatchRepository {
    suspend fun getUpcomingMatches(): List<Match>
//    suspend fun getMatchesBetween(): List<>
}