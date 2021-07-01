package com.asharashenidze.alarmapp.view

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.asharashenidze.alarmapp.model.Alarm
import com.asharashenidze.alarmapp.presenter.IMainPresenter
import java.util.*

class TimePickerFragment(var presenter: IMainPresenter, var adapter: RecyclerAdapter?) : DialogFragment(), TimePickerDialog.OnTimeSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            // Create a new instance of TimePickerDialog and return it
            return TimePickerDialog(activity, this, hour, minute, is24HourFormat(context))
        }

        override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {

            presenter.addAlarm(Alarm(hourOfDay, minute, true))
            adapter?.setData(presenter.getAlarms())
        }
    }