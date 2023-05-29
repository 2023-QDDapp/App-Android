package com.example.qddapp

import android.app.Application
import android.provider.ContactsContract.Data
import com.example.qddapp.Retrofit.Repositorio

class MyApp: Application (){

    val repositorio: Repositorio by lazy { Repositorio(applicationContext) }
    val datos: DataPreferences by lazy { DataPreferences(applicationContext)}

}