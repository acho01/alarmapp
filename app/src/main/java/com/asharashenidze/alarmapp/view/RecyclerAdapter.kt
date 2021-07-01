package com.asharashenidze.alarmapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asharashenidze.alarmapp.R

class RecyclerAdapter(var alarmList: List<String>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return alarmList.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTime.text = alarmList[position]

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTime: TextView
        var itemSwitch: Switch

        init {
            itemTime = itemView.findViewById(R.id.time_view)
            itemSwitch = itemView.findViewById(R.id.switch_view )
        }
    }

    fun setData(data: List<String>) {
        alarmList = listOf()
        alarmList += data
        this.notifyDataSetChanged();
    }

}