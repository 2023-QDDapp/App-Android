package com.example.qddapp

import android.content.Context

class DataPreferences(context: Context) {

    val preferences = context.getSharedPreferences("pref_filter", Context.MODE_PRIVATE)
    val editor = preferences.edit()

    fun guardarToken(token: String)
    {
        editor.putString("token", token)
        editor.apply()
    }


}