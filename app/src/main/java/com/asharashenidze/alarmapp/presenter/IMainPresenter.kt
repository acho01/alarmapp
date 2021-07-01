package com.asharashenidze.alarmapp.presenter

interface IMainPresenter {
    abstract fun updateTheme()
    abstract fun addAlarm(s: String)
    abstract fun getAlarms(): List<String>
}