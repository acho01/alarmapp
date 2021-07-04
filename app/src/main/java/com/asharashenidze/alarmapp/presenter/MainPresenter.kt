package com.asharashenidze.alarmapp.presenter

import android.content.Context
import com.asharashenidze.alarmapp.model.Alarm
import com.asharashenidze.alarmapp.model.MainInteractor
import com.asharashenidze.alarmapp.utils.Theme
import com.asharashenidze.alarmapp.view.IMainView

class MainPresenter(var context: Context, var view: IMainView?) : IMainPresenter{

    private val interactor = MainInteractor( context,this)

    override fun updateTheme() {
        var currentTheme = getCurrentTheme()
        interactor.setCurrentTheme(if (currentTheme == Theme.LIGHT) Theme.DARK else Theme.LIGHT)
        view?.updateView(getCurrentTheme() == Theme.LIGHT)
    }

    override fun addAlarm(alarm: Alarm) {
        interactor.addAlarm(alarm)
        view?.startAlarm(getAlarms().size-1)
    }

    override fun getAlarms(): List<Alarm> {
        return interactor.getAlarmList()
    }

    override fun switchAlarmOnIndex(i: Int) {
        interactor.switchAlarm(i)
    }

    override fun startAlarm(position: Int) {
        view?.startAlarm(position)
    }

    override fun stopAlarm(position: Int) {
        view?.stopAlarm(position)
    }

    private fun getCurrentTheme(): Theme? {
        return if (interactor.getCurrentTheme() == null) Theme.DARK else (interactor.getCurrentTheme())
    }

}