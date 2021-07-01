package com.asharashenidze.alarmapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asharashenidze.alarmapp.R
import com.asharashenidze.alarmapp.model.Alarm
import com.asharashenidze.alarmapp.presenter.MainPresenter

class RecyclerAdapter(var alarmList: List<Alarm>, var presenter: MainPresenter): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return alarmList.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        var alarm = alarmList[position]
        var hour = if (alarm.hour < 10) "0${alarm.hour}" else (alarm.hour)
        var min = if (alarm.minute < 10) "0$alarm.minute" else (alarm.minute)

        holder.itemTime.text = "${hour}:${min}"
        holder.itemSwitch.isChecked = alarm.isOn

        holder.itemSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.switchAlarmOnIndex(position)
            if (isChecked) {
                println("OOOOOOOOOOOOOON" + position)
            } else {
                println("OOOOFFFFFF" + + position)
            }
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTime: TextView
        var itemSwitch: Switch

        init {
            itemTime = itemView.findViewById(R.id.time_view)
            itemSwitch = itemView.findViewById(R.id.switch_view )
        }
    }

    fun setData(data: List<Alarm>) {
        alarmList = listOf()
        alarmList += data
        this.notifyDataSetChanged();
    }

}