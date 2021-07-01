package com.asharashenidze.alarmapp

import android.app.Dialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asharashenidze.alarmapp.presenter.MainPresenter
import com.asharashenidze.alarmapp.view.IMainView
import com.asharashenidze.alarmapp.view.RecyclerAdapter
import com.asharashenidze.alarmapp.view.TimePickerFragment
import java.util.*

class MainActivity : AppCompatActivity(), IMainView {

    private var layoutManager: RecyclerView.LayoutManager? = null;
    private var adapter: RecyclerAdapter? = null

    private var times = listOf<String>("11:22", "22:23")


    private lateinit var themeView: TextView

    private lateinit var headerView: View

    private lateinit var presenter: MainPresenter

    private var TO_DARK = "Switch to dark"

    private var TO_LIGHT = "Switch to light"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this)
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager

        presenter = MainPresenter(this)
        adapter = RecyclerAdapter(presenter.getAlarms())
        recyclerView.adapter = adapter


        initTheme()
        initButton()

    }



    private fun initButton() {
        val addAlarm = findViewById<ImageView>(R.id.add_alarmm)
        addAlarm.setOnClickListener {
            TimePickerFragment(presenter, adapter).show(supportFragmentManager, "timePicker")
        }
    }

    private fun initTheme() {
        themeView = findViewById<Button>(R.id.themeView)
        themeView.text = TO_DARK

        updateView(false)

        themeView.setOnClickListener {
            presenter.updateTheme()
        }
    }

    override fun updateView(toDark: Boolean) {
        val headerColor = if (toDark) getResources().getColor(R.color.light_black) else getResources().getColor(R.color.dark_white)

        headerView = findViewById<View>(R.id.headerBack)
        headerView.setBackgroundColor(headerColor)

        if (toDark) {
            themeView.text = TO_LIGHT
        } else {
            themeView.text = TO_DARK
        }

        headerView.setBackgroundColor(headerColor)

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