package com.asharashenidze.alarmapp.model

import com.asharashenidze.alarmapp.presenter.IMainPresenter
import com.asharashenidze.alarmapp.utils.Theme

class MainInteractor(val presenter: IMainPresenter) {

    private var currentTheme: Theme? = null

    private var alarmList: List<String> = mutableListOf("11:22")

    fun getCurrentTheme(): Theme? {
        return currentTheme
    }

    fun setCurrentTheme(theme: Theme) {
        currentTheme = theme
    }

    fun getAlarmList(): List<String> {
        return alarmList
    }

    fun addAlarm(alarm: String) {
        alarmList += alarm
    }
}