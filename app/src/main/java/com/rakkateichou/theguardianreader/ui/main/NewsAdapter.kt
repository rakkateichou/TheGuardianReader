package com.rakkateichou.theguardianreader.ui.main

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rakkateichou.theguardianreader.api.NewsEntry
import com.rakkateichou.theguardianreader.data.model.Section
import com.rakkateichou.theguardianreader.databinding.SmallNewsCardBinding
import com.rakkateichou.theguardianreader.util.isNightMode
import kotlin.random.Random

class NewsAdapter(private val section: Section) :
    PagingDataAdapter<NewsEntry, NewsAdapter.NewsViewHolder>(NEWS_COMPARATOR) {

    inner class NewsViewHolder(private val binding: SmallNewsCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsEntry) {
            binding.apply {
                val sectionColor = root.context.resources.getColor(section.mainColor, null)
                val commentsIconColor = if (root.context.isNightMode()) Color.WHITE else Color.BLACK
                smallCardTopLine.background = ColorDrawable(sectionColor)
                smallCardTitle.text = news.webTitle
                smallCardCommentIcon.setColorFilter(commentsIconColor, PorterDuff.Mode.SRC_IN)
                smallCardCommentCounter.text = Random.nextInt(100).toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            SmallNewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val NEWS_COMPARATOR = object : DiffUtil.ItemCallback<NewsEntry>() {
            override fun areItemsTheSame(oldItem: NewsEntry, newItem: NewsEntry): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: NewsEntry, newItem: NewsEntry): Boolean =
                oldItem == newItem
        }
    }
}