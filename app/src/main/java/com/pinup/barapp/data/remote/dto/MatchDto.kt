package com.pinup.barapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.pinup.barapp.domain.models.Match

data class MatchResponse(
    @SerializedName("data") val data: List<MatchDto>
)

data class MatchDto(
    @SerializedName("id") val id: Int,
    @SerializedName("starting_at") val startingAt: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("league") val league: LeagueDto?,
    @SerializedName("home_team") val homeTeam: TeamDto?,
    @SerializedName("away_team") val awayTeam: TeamDto?
) {
    fun toDomain(): Match = Match(
        id = id,
        homeName = homeTeam?.name.orEmpty(),
        awayName = awayTeam?.name.orEmpty(),
        homeLogo = homeTeam?.logo,
        awayLogo = awayTeam?.logo,
        time = startingAt.orEmpty(),
        status = status.orEmpty(),
        league = league?.name.orEmpty()
    )
}

data class TeamDto(
    @SerializedName("name") val name: String?,
    @SerializedName("image_path") val logo: String?
)

data class LeagueDto(
    @SerializedName("name") val name: String?
)
