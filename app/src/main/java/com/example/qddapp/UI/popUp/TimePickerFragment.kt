package com.example.qddapp.UI.popUp

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerFragment(val listener: (String) -> Unit): DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)
        val dialog = TimePickerDialog(activity as Context, this, hour, minute, false)
        return dialog
    }

    override fun onTimeSet(view: TimePicker?, hourOnDay: Int, minute: Int) {
        listener("$hourOnDay:$minute")
    }
}

class DatePickerFragment(val listener: (String) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val month: Int = calendar.get(Calendar.MONTH)
        val year: Int = calendar.get(Calendar.YEAR)
        val dialog = DatePickerDialog(activity as Context, this, day, month, year)
        return dialog
    }

    override fun onDateSet(view: DatePicker?, ano: Int, mes: Int, dia: Int) {
        listener("$dia/" + (mes+1).toString() + "/$ano")
    }
}