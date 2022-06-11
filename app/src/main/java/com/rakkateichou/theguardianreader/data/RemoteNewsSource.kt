package com.rakkateichou.theguardianreader.data

import com.rakkateichou.theguardianreader.Constants.DEFAULT_PAGE_SIZE
import com.rakkateichou.theguardianreader.data.model.Section
import com.rakkateichou.theguardianreader.api.NewsEntry
import com.rakkateichou.theguardianreader.api.NewsApi
import javax.inject.Inject

class RemoteNewsSource @Inject constructor(private val newsApi: NewsApi) {
    suspend fun fetchSection(section: Section, page: Int): Result<List<NewsEntry>> {
        val response = newsApi.search(section, DEFAULT_PAGE_SIZE, page)
        return if (response.isSuccessful)
            Result.success(response.body()!!.response.results)
        else
            Result.failure(Throwable(response.errorBody().toString()))
    }
}
