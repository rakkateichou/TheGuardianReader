package com.rakkateichou.theguardianreader.ui.sections

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.rakkateichou.theguardianreader.data.model.CardType
import com.rakkateichou.theguardianreader.data.model.NewsEntry
import com.rakkateichou.theguardianreader.data.model.Section
import com.rakkateichou.theguardianreader.databinding.ImageNewsCardBinding
import com.rakkateichou.theguardianreader.databinding.LargeImageNewsCardBinding
import com.rakkateichou.theguardianreader.databinding.SmallNewsCardBinding
import com.rakkateichou.theguardianreader.util.isNightMode
import kotlin.random.Random

class NewsAdapter(private val section: Section, private val onClick: (NewsEntry, CardType) -> Unit) :
    PagingDataAdapter<NewsEntry, NewsAdapter.NewsViewHolder>(NEWS_COMPARATOR) {

    inner class NewsViewHolder(
        private val cardType: CardType,
        private val binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsEntry) {
            // later cardType.bindingClass.cast(binding)?
            val sectionColor =
                binding.root.resources.getColor(section.mainColor, null)
            val sectionDrawable = ColorDrawable(sectionColor)
//            val sectionColorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(sectionColor, BlendModeCompat.SRC_IN)

            val commentsIconColor =
                if (binding.root.context.isNightMode()) Color.WHITE else Color.BLACK

            val random = Random.nextInt(100).toString()

            binding.root.setOnClickListener { onClick(news, cardType) }

            when (cardType) {
                CardType.SMALL -> {
                    (binding as SmallNewsCardBinding).apply {
                        smallCardTopLine.background = sectionDrawable
                        smallCardTitle.text = news.webTitle
                        smallCardCommentIcon.setColorFilter(commentsIconColor)
                        smallCardCommentCounter.text = random
                    }
                }
                CardType.MEDIUM -> {
                    (binding as ImageNewsCardBinding).apply {
                        imageCardTopLine.background = sectionDrawable
                        imageCardTitle.text = news.webTitle
                        imageCardCommentIcon.setColorFilter(commentsIconColor)
                        imageCardCommentCounter.text = random
                        val circularProgressDrawable =
                            CircularProgressDrawable(binding.root.context).apply {
                                strokeWidth = 5f
                                centerRadius = 30f
//                            colorFilter = sectionColorFilter
                                start()
                            }
                        Glide.with(root.context).load(news.fields?.thumbnail)
                            .placeholder(circularProgressDrawable).into(imageCardImage)
                    }
                }
                CardType.LARGE -> {
                    (binding as LargeImageNewsCardBinding).apply {
                        largeImageCardTopLine.background = sectionDrawable
                        largeImageCardTitle.text = news.webTitle
                        largeImageCardCommentIcon.setColorFilter(commentsIconColor)
                        largeImageCardCommentCounter.text = random
                        val circularProgressDrawable =
                            CircularProgressDrawable(binding.root.context).apply {
                                strokeWidth = 5f
                                centerRadius = 30f
//                            colorFilter = sectionColorFilter
                                start()
                            }
                        Glide.with(root.context).load(news.fields?.thumbnail)
                            .placeholder(circularProgressDrawable)
                            .into(largeImageCardImage)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val cardType = CardType.getCardType(viewType)

        val inflatingMethod: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding =
            when (cardType) {
                CardType.SMALL -> SmallNewsCardBinding::inflate
                CardType.MEDIUM -> ImageNewsCardBinding::inflate
                CardType.LARGE -> LargeImageNewsCardBinding::inflate
            }
        val binding = inflatingMethod(LayoutInflater.from(parent.context), parent, false)

        return NewsViewHolder(cardType, binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position) ?: return CardType.DEFAULT_VIEW_TYPE
        return CardType.getViewType(item)
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