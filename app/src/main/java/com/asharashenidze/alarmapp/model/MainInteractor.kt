package com.asharashenidze.alarmapp.model

import android.content.Context
import android.content.SharedPreferences
import com.asharashenidze.alarmapp.presenter.IMainPresenter
import com.asharashenidze.alarmapp.utils.Theme

class MainInteractor(val sharedPreferences: SharedPreferences, val presenter: IMainPresenter) {

    private var currentTheme: Theme? = null

    private var alarmList: List<Alarm> = mutableListOf(Alarm("12".toInt(), "23".toInt(), true))


    fun getCurrentTheme(): Theme? {
        return currentTheme
    }

    fun setCurrentTheme(theme: Theme) {
        currentTheme = theme
    }

    fun getAlarmList(): List<Alarm> {
        var list = listOf<Alarm>()

        for (entry in sharedPreferences.all) {
            var hour = entry.value.toString().split(":")[0].toInt()
            var min = entry.value.toString().split(":")[1].toInt()
            var on = entry.value.toString().split(":")[2].toBoolean()
            list += Alarm(hour, min, on)
        }

        return alarmList
    }

    fun addAlarm(alarm: Alarm) {
        print(alarm.hour)
        var editor = sharedPreferences.edit()
        editor.putString(sharedPreferences.all.size.toString(), alarm.toString()+":"+alarm.isOn)
        alarmList += alarm
        editor.commit()
    }

    fun switchAlarm(index: Int) {
        alarmList[index].isOn = !alarmList[index].isOn
    }
}