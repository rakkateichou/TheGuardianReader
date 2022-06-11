package com.rakkateichou.theguardianreader.api

data class NewsResponse(
    val response: NewsInnerResponse
)

data class NewsInnerResponse(
    val currentPage: Int,
    val orderBy: String,
    val pageSize: Int,
    val pages: Int,
    val results: List<NewsEntry>,
    val startIndex: Int,
    val status: String,
    val total: Int,
    val userTier: String
)

data class NewsEntry(
    val apiUrl: String,
    val id: String,
    val isHosted: Boolean,
    val pillarId: String,
    val pillarName: String,
    val sectionId: String,
    val sectionName: String,
    val type: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String
)