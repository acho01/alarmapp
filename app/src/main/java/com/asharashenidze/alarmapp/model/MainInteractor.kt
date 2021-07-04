package com.asharashenidze.alarmapp.model

import android.content.Context
import android.content.SharedPreferences
import com.asharashenidze.alarmapp.presenter.IMainPresenter
import com.asharashenidze.alarmapp.utils.Theme

class MainInteractor(val context: Context, val presenter: IMainPresenter) {

    private var currentTheme: Theme? = null

    private var alarmList: List<Alarm> = mutableListOf(Alarm(12, 23, true))

    val sharedPreferences: SharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE)

    lateinit var editor: SharedPreferences.Editor

    fun MainInteractor() {
        editor = sharedPreferences.edit()
    }

    fun getCurrentTheme(): Theme? {
        return currentTheme
    }

    fun setCurrentTheme(theme: Theme) {
        currentTheme = theme
    }

    fun getAlarmList(): List<Alarm> {
        return alarmList
    }

    fun addAlarm(alarm: Alarm) {
        print(alarm.hour)
        alarmList += alarm
    }

    fun switchAlarm(index: Int) {
        alarmList[index].isOn = !alarmList[index].isOn
    }
}