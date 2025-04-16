package com.practicum.testappshop.app

import android.app.Application
import com.practicum.testappshop.di.AppComponent
import com.practicum.testappshop.di.AppModule
import com.practicum.testappshop.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent //dagger startuet
            .builder()
            .appModule(AppModule(context = this))
            .build()

        val databaseInitializer = DatabaseInitializer(this)
        databaseInitializer.populateDatabase()

    }
}