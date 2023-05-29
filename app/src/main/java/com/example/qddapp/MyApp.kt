package com.example.qddapp

import android.app.Application
import com.example.qddapp.Retrofit.Repositorio

class MyApp: Application (){

    val repositorio: Repositorio by lazy { Repositorio(applicationContext) }

    companion object {
        lateinit var datos: DataPreferences
    }

    override fun onCreate() {
        super.onCreate()
        datos = DataPreferences(applicationContext)
    }
}