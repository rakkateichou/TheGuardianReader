package com.rakkateichou.theguardianreader.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rakkateichou.theguardianreader.data.model.Section

class CategoryPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = Section.values().size

    override fun createFragment(position: Int): Fragment = SectionFragment()
}