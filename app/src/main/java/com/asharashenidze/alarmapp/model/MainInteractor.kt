package com.asharashenidze.alarmapp.model

import com.asharashenidze.alarmapp.presenter.IMainPresenter
import com.asharashenidze.alarmapp.utils.Theme

class MainInteractor(val presenter: IMainPresenter) {

    private var currentTheme: Theme? = null

    fun getCurrentTheme(): Theme? {
        return currentTheme
    }

    fun setCurrentTheme(theme: Theme) {
        currentTheme = theme
    }
}