package com.pinup.barapp.domain.models

data class Match(
    val id: Int,
    val homeName: String,
    val awayName: String,
    val homeLogo: String?,
    val awayLogo: String?,
    val time: String,
    val status: String,
    val league: String
)
