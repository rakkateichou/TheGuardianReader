package com.rakkateichou.theguardianreader.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.rakkateichou.theguardianreader.Constants.DEFAULT_PAGE_SIZE
import com.rakkateichou.theguardianreader.data.model.Section
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val remoteNewsSource: RemoteNewsSource){

    fun getSectionLiveData(section: Section) =
        Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                maxSize = 200,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GuardianPagingSource(remoteNewsSource, section) }
        ).liveData
}