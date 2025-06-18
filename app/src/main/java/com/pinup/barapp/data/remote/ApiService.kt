package com.pinup.barapp.data.remote

import com.pinup.barapp.data.remote.dto.MatchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("fixtures/between")
    suspend fun getMatchesNext7Days(
        @Query("from") fromDate: String,
        @Query("to") toDate: String,
        @Query("api_token") apiKey: String
    ): Response<MatchResponse>
}
