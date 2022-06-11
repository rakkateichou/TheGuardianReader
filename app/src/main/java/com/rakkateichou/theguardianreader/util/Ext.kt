package com.rakkateichou.theguardianreader.util

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayout
import com.rakkateichou.theguardianreader.R

fun TabLayout.changeSelectedColor(@ColorRes color: Int) {
    val selectedColor = ResourcesCompat.getColor(context.resources, color, null)
    setSelectedTabIndicatorColor(selectedColor)
    setTabTextColors(
        ResourcesCompat.getColor(context.resources, R.color.brightness_100, null),
        selectedColor
    )
}

fun Context.isNightMode(): Boolean =
    resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES