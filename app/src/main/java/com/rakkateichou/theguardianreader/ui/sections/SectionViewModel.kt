package com.rakkateichou.theguardianreader.ui.sections

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rakkateichou.theguardianreader.Constants.DEFAULT_SECTION
import com.rakkateichou.theguardianreader.data.model.NewsEntry
import com.rakkateichou.theguardianreader.data.NewsRepository
import com.rakkateichou.theguardianreader.data.model.Section
import timber.log.Timber
import javax.inject.Inject

class SectionViewModel : ViewModel() {

    @Inject
    lateinit var newsRepository: NewsRepository // have to be injected in activity

    private val _currentSection = MutableLiveData(DEFAULT_SECTION)
    val currentSection: LiveData<Section> get() = _currentSection

    val news = _currentSection.switchMap { section ->
        newsRepository.getSectionLiveData(section).cachedIn(viewModelScope)
    }

    fun fetchNews(section: Section): LiveData<PagingData<NewsEntry>> {
        _currentSection.value = section
        return news
    }
}