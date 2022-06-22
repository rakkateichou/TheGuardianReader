package com.rakkateichou.theguardianreader.ui.sections

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rakkateichou.theguardianreader.Constants.DEFAULT_SECTION
import com.rakkateichou.theguardianreader.R
import com.rakkateichou.theguardianreader.data.model.Section
import com.rakkateichou.theguardianreader.databinding.FragmentMainBinding
import com.rakkateichou.theguardianreader.ui.main.MainActivity
import com.rakkateichou.theguardianreader.util.changeSelectedColor
import com.rakkateichou.theguardianreader.util.findInViewPager

class SectionsContainerFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    var selectingTabFromCode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        val activity = requireActivity() as MainActivity

        binding.mainViewPager.apply {
            adapter = CategoryPagerAdapter(requireActivity())
            offscreenPageLimit = Section.values().size - 1
        }

        TabLayoutMediator(binding.mainTabLayout, binding.mainViewPager) { tab, position ->
            tab.text = Section.fromInt(position)?.name ?: DEFAULT_SECTION.name
        }.attach()

        // setting up tab layout
        binding.mainTabLayout.apply {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val currentSection = Section.fromInt(tab.position) ?: DEFAULT_SECTION
                    changeSelectedColor(currentSection.mainColor)
                    activity.mainViewModel.setCurrentSection(currentSection)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {
                    if (!selectingTabFromCode)
                        (parentFragmentManager.findInViewPager(tab.position) as SectionFragment)
                            .updateNews()
                }

            })
            run {
                selectingTabFromCode = true
                val previousTab =
                    activity.mainViewModel.currentSection.ordinal
//              reselecting current tab to update colors
                selectTab(getTabAt(1))
//              when switching the night mode tab layout resets to the first tab
//              so we get what tab we was at before from mainViewModel
//              todo figure out why it is not working as expected
                selectTab(getTabAt(previousTab))
                selectingTabFromCode = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sections_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh_item -> {
                (parentFragmentManager.findInViewPager(binding.mainTabLayout.selectedTabPosition) as SectionFragment)
                    .updateNews()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}