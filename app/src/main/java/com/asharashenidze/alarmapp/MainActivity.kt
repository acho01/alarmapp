package com.asharashenidze.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asharashenidze.alarmapp.presenter.MainPresenter
import com.asharashenidze.alarmapp.view.IMainView
import com.asharashenidze.alarmapp.view.RecyclerAdapter
import com.asharashenidze.alarmapp.view.TimePickerFragment
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.util.*

class MainActivity : AppCompatActivity(), IMainView {

    private var layoutManager: RecyclerView.LayoutManager? = null;
    private var adapter: RecyclerAdapter? = null

    private var times = listOf<String>("11:22", "22:23")


    private lateinit var themeView: TextView

    private lateinit var headerView: View

    private lateinit var presenter: MainPresenter

    private lateinit var sharedPreferences: SharedPreferences

    private var TO_DARK = "Switch to dark"

    private var TO_LIGHT = "Switch to light"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutManager = LinearLayoutManager(this)
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager

        sharedPreferences = this.getSharedPreferences("data", Context.MODE_PRIVATE)

        presenter = MainPresenter(sharedPreferences, this)
        adapter = RecyclerAdapter(presenter.getAlarms(), presenter)
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
        val backgorundColor = if (isDark) getResources().getColor(R.color.light_black) else Color.WHITE
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

    override fun stopAlarm(position: Int) {
        var pi = PendingIntent.getBroadcast(
                this,
                position,
                Intent(AlarmReceiver.ALARM_ACTION_NAME).apply {
                    `package` = packageName
                },
                0
        )

        var alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pi)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun startAlarm(position: Int) {
        var intent = Intent(AlarmReceiver.ALARM_ACTION_NAME).apply {
            `package` = packageName
        }

        intent.putExtra("asd", "sadd")

        var pi = PendingIntent.getBroadcast(
                this,
                position,
                intent,
                0
        )

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var today =  sdf.format(Date()) + " "


        var dateTimeStr = today+presenter.getAlarms()[position].toString()

        var millis = System.currentTimeMillis()

        val nowInMillis = try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val dateTime: LocalDateTime? = LocalDateTime.parse(dateTimeStr, formatter)

            ((dateTime?.toEpochSecond(ZoneOffset.UTC)?.times(1000) ?: 0)
                    + (dateTime?.get(ChronoField.MILLI_OF_SECOND) ?: 0)) / 60
        } catch (e: Exception) {
            System.currentTimeMillis()
        }



        var alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, nowInMillis-System.currentTimeMillis(), pi)
    }

    companion object {
        const val ALARM_REQUEST_CODE = 344
    }

}