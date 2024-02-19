package com.example.mobiledevelopmentcourselabapp

import android.app.Application
import com.example.mobiledevelopmentcourselabapp.di.AppComponent
import com.example.mobiledevelopmentcourselabapp.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }

    companion object {
        var appComponent: AppComponent? = null
    }
}