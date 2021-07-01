package com.asharashenidze.alarmapp.presenter

import com.asharashenidze.alarmapp.model.MainInteractor
import com.asharashenidze.alarmapp.utils.Theme
import com.asharashenidze.alarmapp.view.IMainView

class MainPresenter(var view: IMainView?) : IMainPresenter{

    private val interactor = MainInteractor(this)

    override fun updateTheme() {
        var currentTheme = getCurrentTheme()
        interactor.setCurrentTheme(if (currentTheme == Theme.LIGHT) Theme.DARK else Theme.LIGHT)
        view?.updateView(getCurrentTheme() == Theme.LIGHT)
    }

    override fun addAlarm(s: String) {
        interactor.addAlarm(s)
    }

    override fun getAlarms(): List<String> {
        return interactor.getAlarmList()
    }


    private fun getCurrentTheme(): Theme? {
        return if (interactor.getCurrentTheme() == null) Theme.DARK else (interactor.getCurrentTheme())
    }

}