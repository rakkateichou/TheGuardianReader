package com.rakkateichou.theguardianreader.data.model

import androidx.viewbinding.ViewBinding
import com.rakkateichou.theguardianreader.databinding.ImageNewsCardBinding
import com.rakkateichou.theguardianreader.databinding.LargeImageNewsCardBinding
import com.rakkateichou.theguardianreader.databinding.SmallNewsCardBinding
import kotlin.reflect.KClass

enum class CardType(
    val bindingClass: KClass<out ViewBinding>,
    private val toneIds: List<String>
) {
    SMALL(SmallNewsCardBinding::class, listOf()),
    MEDIUM(ImageNewsCardBinding::class, listOf()),
    LARGE(LargeImageNewsCardBinding::class, listOf());

    companion object {

        fun getCardType(viewType: Int): CardType = values()[viewType]

        fun getViewType(newsEntry: NewsEntry): Int {
//            val possibleTypes = mutableListOf<CardType>()
//            for (tag in newsEntry.tags) {
//                possibleTypes.add(getCardTypeByToneId(tag.id))
//            }
//            return possibleTypes.last()

            // later question: is the largest type is the last in tags?
            val lastId = newsEntry.tags.lastOrNull()?.id
            val hasThumbnail = newsEntry.fields?.thumbnail != null
            lastId?.let {
                values().forEach { type ->
                    if (lastId in type.toneIds && hasThumbnail)
                        return type.ordinal
                }
            }
            return if (hasThumbnail) MEDIUM.ordinal else SMALL.ordinal
        }

        val DEFAULT_VIEW_TYPE = MEDIUM.ordinal
    }
}