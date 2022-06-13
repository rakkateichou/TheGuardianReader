package com.rakkateichou.theguardianreader.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rakkateichou.theguardianreader.Constants.DEFAULT_SECTION
import com.rakkateichou.theguardianreader.data.NewsRepository
import com.rakkateichou.theguardianreader.data.model.Section
import javax.inject.Inject

class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val currentSection = MutableLiveData(DEFAULT_SECTION)

    fun setCurrentSection(section: Section) {
        currentSection.value = section
    }

}