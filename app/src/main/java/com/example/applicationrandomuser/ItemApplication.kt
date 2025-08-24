package com.example.applicationrandomuser

import android.app.Application
import com.example.applicationrandomuser.presentation.di.ApplicationComponent
import com.example.applicationrandomuser.presentation.di.DaggerApplicationComponent

class ItemApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.factory().create(applicationContext)
    }

}
