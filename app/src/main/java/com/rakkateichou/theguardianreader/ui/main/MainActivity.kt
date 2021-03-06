package com.rakkateichou.theguardianreader.ui.main

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rakkateichou.theguardianreader.R
import com.rakkateichou.theguardianreader.TheGuardianReaderApp
import com.rakkateichou.theguardianreader.databinding.ActivityMainBinding
import com.rakkateichou.theguardianreader.ui.sections.SectionsContainerFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mainViewModel: MainViewModel

    private var isSearchBarOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as TheGuardianReaderApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        setupUi()
    }

    private fun setupUi() {
        supportFragmentManager.apply {
            findFragmentByTag(SECTIONS_FRAGMENT_TAG) ?: beginTransaction()
                .add(binding.mainFragmentContainer.id, SectionsContainerFragment(), SECTIONS_FRAGMENT_TAG)
                .commitNow()
        }

        // enabling changing transition and disabling appearing transition
        // so search edit text doesn't flicker
        binding.searchLayout.layoutTransition.apply {
            disableTransitionType(LayoutTransition.APPEARING)
            disableTransitionType(LayoutTransition.DISAPPEARING)
            enableTransitionType(LayoutTransition.CHANGING)
        }

        binding.toolbarLogo.setOnClickListener {
            supportFragmentManager.findFragmentByTag(SECTIONS_FRAGMENT_TAG)?.let {
                (it as SectionsContainerFragment).binding.mainTabLayout.apply {
                    selectTab(getTabAt(0))
                }
            }
        }

        // setting up search button
//        binding.toolbarSearchIcon.setOnClickListener { if (!isSearchBarOpen) openSearch() }

        // setting up apppager
//        binding.mainViewPager.adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun openSearch() {
        isSearchBarOpen = true
        binding.searchLayout.visibility = View.VISIBLE
        binding.searchEditText.visibility = View.VISIBLE
    }

    private fun closeSearch() {
        isSearchBarOpen = false
        binding.searchEditText.visibility = View.GONE
        binding.searchLayout.visibility = View.INVISIBLE
    }


    override fun onBackPressed() {
        if (isSearchBarOpen) closeSearch()
        else super.onBackPressed()
    }

    companion object {
        private const val SECTIONS_FRAGMENT_TAG = "main_fragment"
    }

}


//    private fun openSearch() {
//        isSearchBarOpen = true
//        binding.searchTextLayout.apply {
//            visibility = View.VISIBLE
//            // expanding edit text to take full width
//            layoutParams = ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.MATCH_PARENT,
//                0
//            )
//        }
//        ConstraintSet().apply {
//            clone(binding.toolbarConstraintLayout)
//
//            // expanding edit text to take full height
//            connect(
//                binding.searchTextLayout.id, ConstraintSet.TOP,
//                ConstraintSet.PARENT_ID, ConstraintSet.TOP
//            )
//            connect(
//                binding.searchTextLayout.id, ConstraintSet.BOTTOM,
//                ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM
//            )
//
//            applyTo(binding.toolbarConstraintLayout)
//        }
//        binding.searchTextLayout.requestFocus()
//    }

//    private fun closeSearch() {
//        isSearchBarOpen = false
//        binding.searchTextLayout.apply {
//            layoutParams = ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                0
//            )
//            visibility = View.INVISIBLE
//        }
//    }