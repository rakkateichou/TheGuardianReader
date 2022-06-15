package com.rakkateichou.theguardianreader.data.model

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
    val fields: Fields,
    val id: String,
    val isHosted: Boolean,
    val pillarId: String,
    val pillarName: String,
    val sectionId: String,
    val sectionName: String,
    val tags: List<Tag>,
    val type: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String
)

data class Fields(
    val byline: String,
    val commentable: String,
    val headline: String
)

data class Tag(
    val apiUrl: String,
    val id: String,
    val references: List<Any>,
    val type: String,
    val webTitle: String,
    val webUrl: String
)