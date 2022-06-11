package com.rakkateichou.theguardianreader.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakkateichou.theguardianreader.Constants.DEFAULT_SECTION
import com.rakkateichou.theguardianreader.R
import com.rakkateichou.theguardianreader.TheGuardianReaderApp
import com.rakkateichou.theguardianreader.api.NewsEntry
import com.rakkateichou.theguardianreader.data.model.Section
import com.rakkateichou.theguardianreader.databinding.FragmentSectionBinding
import javax.inject.Inject

class SectionFragment(private val section: Section) : Fragment(R.layout.fragment_section) {

    constructor() : this(DEFAULT_SECTION) {}

    @Inject
    lateinit var viewModel: MainViewModel

    private var _binding: FragmentSectionBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as TheGuardianReaderApp).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSectionBinding.bind(view)

        val adapter = NewsAdapter(section)

        binding.apply {
            sectionRv.setHasFixedSize(true)
            sectionRv.layoutManager = LinearLayoutManager(this@SectionFragment.requireContext())
            sectionRv.adapter = adapter
//            sectionRv.background = ColorDrawable(resources.getColor(section.mainColor))
        }

        viewModel.news.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}