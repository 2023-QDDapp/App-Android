package com.example.qddapp

import android.app.Application
import com.example.qddapp.Retrofit.Repositorio

class MyApp: Application (){

    val repositorio: Repositorio by lazy { Repositorio(applicationContext) }
}