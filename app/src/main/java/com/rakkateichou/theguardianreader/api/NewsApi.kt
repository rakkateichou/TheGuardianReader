package com.rakkateichou.theguardianreader.api

import com.rakkateichou.theguardianreader.data.model.NewsResponse
import com.rakkateichou.theguardianreader.data.model.Section
import com.rakkateichou.theguardianreader.di.IdOfSection
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("search")
    suspend fun search(
        @Query("section") @IdOfSection section: Section,
        @Query("page-size") pageSize: Int,
        @Query("page") page: Int
    ): Response<NewsResponse>
}