package com.example.lesson1111

import android.app.Application
import com.example.lesson1111.di.AppComponent
import com.example.lesson1111.di.DaggerAppComponent

class MyApplication : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
