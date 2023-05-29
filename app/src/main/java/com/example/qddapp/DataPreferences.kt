package com.example.qddapp

import android.content.Context

class DataPreferences(context: Context) {

    val preferences = context.getSharedPreferences("pref_filter", Context.MODE_PRIVATE)
    val editor = preferences.edit()

    fun guardarToken(token: String) {
        editor.putString("token", token)
        editor.apply()
    }

    fun guardarUserId(id: Int) {
        editor.putInt("id_usuario", id)
        editor.apply()
    }

    fun sacarUserId() = preferences.getInt("id_usuario", 0)

    fun eliminarDatos() {
        editor.clear()
        editor.apply()
    }
}