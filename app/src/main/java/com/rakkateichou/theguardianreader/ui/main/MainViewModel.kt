package com.rakkateichou.theguardianreader.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rakkateichou.theguardianreader.Constants.DEFAULT_SECTION
import com.rakkateichou.theguardianreader.data.NewsRepository
import com.rakkateichou.theguardianreader.data.model.Section
import javax.inject.Inject

class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
//    private val _newsLiveData  = MutableLiveData<NewsListResult>()
//    val newsLiveData: LiveData<NewsListResult> = _newsLiveData
//
//    fun fetchSection(section: Section, page: Int = 1): LiveData<NewsListResult> {
//        viewModelScope.launch {
//            val result = newsRepository.fetchSection(section, page)
//            _newsLiveData.postValue(result)
//        }
//        return newsLiveData
//    }

//    val sectionViewModels =
//        List(Section.values().size) {
//            SectionViewModel(
//                newsRepository,
//                Section.fromInt(it) ?: DEFAULT_SECTION
//            )
//        }
//
//    private val currentSection = MutableLiveData(DEFAULT_SECTION)
//
//    fun fetchSection(section: Section) {
//        currentSection.value = section
//    }

    private val currentSection = MutableLiveData(DEFAULT_SECTION)

    val news = currentSection.switchMap { section ->
        newsRepository.getSectionLiveData(section).cachedIn(viewModelScope)
    }

    fun fetchSection(section: Section) {
        currentSection.value = section
    }

    companion object {
        private val DEFAULT_SECTION = Section.News
    }
}