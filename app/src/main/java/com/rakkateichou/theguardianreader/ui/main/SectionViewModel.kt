package com.rakkateichou.theguardianreader.ui.main

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rakkateichou.theguardianreader.Constants.DEFAULT_SECTION
import com.rakkateichou.theguardianreader.data.model.NewsEntry
import com.rakkateichou.theguardianreader.data.NewsRepository
import com.rakkateichou.theguardianreader.data.model.Section
import javax.inject.Inject

class SectionViewModel : ViewModel() {

    @Inject
    lateinit var newsRepository: NewsRepository // have to be injected in activity

    private val currentSection = MutableLiveData(DEFAULT_SECTION)

    val news = currentSection.switchMap { section ->
        newsRepository.getSectionLiveData(section).cachedIn(viewModelScope)
    }

    fun fetchNews(section: Section): LiveData<PagingData<NewsEntry>> {
        currentSection.value = section
        return news
    }
}