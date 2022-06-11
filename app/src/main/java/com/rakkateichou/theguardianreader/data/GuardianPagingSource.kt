package com.rakkateichou.theguardianreader.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rakkateichou.theguardianreader.api.NewsEntry
import com.rakkateichou.theguardianreader.data.model.Section

class GuardianPagingSource(
    private val newsRemoteNewsSource: RemoteNewsSource,
    private val section: Section
) : PagingSource<Int, NewsEntry>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsEntry> {
        val position = params.key ?: 1

        val response = newsRemoteNewsSource.fetchSection(section, position)
        return response.fold(
            onSuccess = { news ->
                LoadResult.Page(
                    data = news,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (news.isEmpty()) null else position + 1
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )

    }

    override fun getRefreshKey(state: PagingState<Int, NewsEntry>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}