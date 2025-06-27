package com.pinup.barapp.data.remote.apiservices

import com.pinup.barapp.data.remote.dto.MatchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("fixtures/between/{from}/{to}?include=participants;participants.meta;league")
    suspend fun getMatchesNext7Days(
        @Path("from") fromDate: String,
        @Path("to") toDate: String,
        @Query("api_token") apiKey: String,
        @Query("include") include: String = "participants;league"
    ): Response<MatchResponse>

}