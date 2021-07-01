package com.asharashenidze.alarmapp.presenter

import com.asharashenidze.alarmapp.model.Alarm

interface IMainPresenter {
    fun updateTheme()
    fun addAlarm(alarm: Alarm)
    fun getAlarms(): List<Alarm>
    fun switchAlarmOnIndex(i: Int)
}