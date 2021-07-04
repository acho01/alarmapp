package com.asharashenidze.alarmapp.view

interface IMainView {
    fun updateView(toDark: Boolean)
    fun startAlarm(position: Int)
    fun stopAlarm(position: Int)
}