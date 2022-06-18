package com.rakkateichou.theguardianreader.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.color.MaterialColors
import com.rakkateichou.theguardianreader.R
import com.rakkateichou.theguardianreader.ui.main.MainActivity
import com.rakkateichou.theguardianreader.util.isNightMode

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (isNightMode()) {
            val nightColor = MaterialColors.getColor(this, R.attr.colorPrimarySurface, Color.BLACK)
            window.statusBarColor = nightColor
        }
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 250L)
    }
}