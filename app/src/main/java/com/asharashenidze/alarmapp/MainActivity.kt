package com.asharashenidze.alarmapp

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.asharashenidze.alarmapp.utils.Theme
import com.asharashenidze.alarmapp.view.IMainView

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var themeView: TextView

    private var currentTheme = Theme.LIGHT

    private var TO_DARK = "Switch to dark"

    private var TO_LIGHT = "Switch to light"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initTheme()

    }

    private fun initTheme() {
        themeView = findViewById<Button>(R.id.themeView)
        themeView.text = TO_DARK

        themeView.setOnClickListener {
            updateView(currentTheme)
        }
    }

    override fun updateView(view: Theme) {
        val toDark = currentTheme == Theme.LIGHT

        if (toDark) {
            currentTheme = Theme.DARK
            themeView.text = TO_LIGHT
        } else {
            currentTheme = Theme.LIGHT
            themeView.text = TO_DARK
        }

        val view = findViewById<View>(android.R.id.content) as ViewGroup
        recursiveLoopChildren(toDark, view)
    }

    private fun recursiveLoopChildren(isDark: Boolean, parent: ViewGroup) {
        val backgorundColor = if (isDark) Color.BLACK else Color.WHITE
        val widgetColor = if (isDark) Color.WHITE else Color.BLACK
        println("A")
        for (child in parent.children) {
            if (child is ViewGroup) {
                child.setBackgroundColor(backgorundColor)
                recursiveLoopChildren(isDark, child);
            } else {
                if (child is TextView) {
                    child.setTextColor(widgetColor);
                } else if (child is ImageView) {
                    child.setColorFilter(widgetColor, PorterDuff.Mode.SRC_ATOP)
                }
            }

        }
    }


}