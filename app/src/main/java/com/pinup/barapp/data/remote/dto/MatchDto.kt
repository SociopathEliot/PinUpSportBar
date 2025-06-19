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
    @SerializedName("participants") val participants: List<ParticipantDto>?,
    @SerializedName("league") val league: LeagueDto?,
) {
    fun toDomain(): Match {
        val home = participants?.getOrNull(0)
        val away = participants?.getOrNull(1)
        return Match(
            id = id,
            homeName = home?.name.orEmpty(),
            awayName = away?.name.orEmpty(),
            homeLogo = home?.imagePath,
            awayLogo = away?.imagePath,
            time = startingAt.orEmpty(),
            status = status.orEmpty(),
            league = league?.name.orEmpty()
        )
    }
}


data class ParticipantDto(
    @SerializedName("name") val name: String?,
    @SerializedName("image_path") val imagePath: String?,
    @SerializedName("meta") val meta: MetaDto?
)

data class MetaDto(
    @SerializedName("location") val location: String?
)


data class LeagueDto(
    @SerializedName("name") val name: String?
)
