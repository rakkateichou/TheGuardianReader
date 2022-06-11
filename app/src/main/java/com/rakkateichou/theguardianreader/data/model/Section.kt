package com.rakkateichou.theguardianreader.data.model

import androidx.annotation.ColorRes
import com.rakkateichou.theguardianreader.R

enum class Section(
    val id : String,
    @ColorRes val darkColor: Int,
    @ColorRes val mainColor: Int,
    @ColorRes val brightColor: Int,
    @ColorRes val pastelColor: Int,
    @ColorRes val fadedColor: Int
) {
    News(
        "news",
        R.color.news_dark,
        R.color.news_main,
        R.color.news_bright,
        R.color.news_pastel,
        R.color.news_faded
    ),
    Opinion(
        "commentisfree",
        R.color.opinion_dark,
        R.color.opinion_main,
        R.color.opinion_bright,
        R.color.opinion_pastel,
        R.color.opinion_faded
    ),
    Sport(
        "sport",
        R.color.sport_dark,
        R.color.sport_main,
        R.color.sport_bright,
        R.color.sport_pastel,
        R.color.sport_faded
    ),
    Culture(
        "culture",
        R.color.culture_dark,
        R.color.culture_main,
        R.color.culture_bright,
        R.color.culture_pastel,
        R.color.culture_faded
    ),
    Lifestyle(
        "lifeandstyle",
        R.color.lifestyle_dark,
        R.color.lifestyle_main,
        R.color.lifestyle_bright,
        R.color.lifestyle_pastel,
        R.color.lifestyle_faded
    );

    companion object {
        fun fromInt(value: Int) = values().find { it.ordinal == value }
    }
}