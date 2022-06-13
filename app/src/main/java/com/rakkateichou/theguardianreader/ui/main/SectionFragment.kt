package com.rakkateichou.theguardianreader.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakkateichou.theguardianreader.Constants.DEFAULT_SECTION
import com.rakkateichou.theguardianreader.R
import com.rakkateichou.theguardianreader.TheGuardianReaderApp
import com.rakkateichou.theguardianreader.data.model.Section
import com.rakkateichou.theguardianreader.databinding.FragmentSectionBinding

class SectionFragment(private val section: Section) : Fragment(R.layout.fragment_section) {

    constructor() : this(DEFAULT_SECTION)

    lateinit var viewModel: SectionViewModel

    private var _binding: FragmentSectionBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[section.id, SectionViewModel::class.java]
        (requireActivity().applicationContext as TheGuardianReaderApp).appComponent.inject(viewModel)

        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSectionBinding.bind(view)

        val adapter = NewsAdapter(section)

        binding.apply {
            sectionRv.setHasFixedSize(true)
            sectionRv.layoutManager = LinearLayoutManager(this@SectionFragment.requireContext())
            sectionRv.adapter = adapter
        }

        updateNews()
        viewModel.news.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    fun updateNews() {
        viewModel.fetchNews(section)
        binding.sectionRv.smoothScrollToPosition(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}