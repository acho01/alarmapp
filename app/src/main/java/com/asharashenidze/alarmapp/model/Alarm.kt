package com.asharashenidze.alarmapp.model

data class Alarm(val hour: Int, val minute: Int, var isOn: Boolean) {
    override fun toString(): String {
        return "$hour:$minute"
    }
}