package com.rakkateichou.theguardianreader.ui.main
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.paging.cachedIn
//import com.rakkateichou.theguardianreader.data.NewsRepository
//import com.rakkateichou.theguardianreader.data.model.Section
//
//class SectionViewModel(
//    newsRepository: NewsRepository,
//    section: Section
//) : ViewModel() {
//
//    val news = newsRepository.getSectionLiveData(section).cachedIn(viewModelScope)
//
////    val news = currentSection.switchMap { section ->
////        newsRepository.getSectionLiveData(section).cachedIn(viewModelScope)
////    }
//}