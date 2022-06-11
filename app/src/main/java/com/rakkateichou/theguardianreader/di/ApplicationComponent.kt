package com.rakkateichou.theguardianreader.di

import com.rakkateichou.theguardianreader.ui.main.MainActivity
import com.rakkateichou.theguardianreader.ui.main.SectionFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: SectionFragment)
}