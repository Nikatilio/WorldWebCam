package com.enzo.wwcam.application

import android.app.Application
import com.enzo.wwcam.dagger.AppComponent
import com.enzo.wwcam.dagger.DaggerAppComponent

class WebcamApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
//        appComponent.inject(this)
    }
}