package com.asharashenidze.alarmapp.model

import com.asharashenidze.alarmapp.presenter.IMainPresenter
import com.asharashenidze.alarmapp.utils.Theme

class MainInteractor(val presenter: IMainPresenter) {

    private var currentTheme: Theme? = null

    private var alarmList: List<Alarm> = mutableListOf(Alarm(12, 23, true))

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
        alarmList += alarm
    }

    fun switchAlarm(index: Int) {
        alarmList[index].isOn = !alarmList[index].isOn
    }
}