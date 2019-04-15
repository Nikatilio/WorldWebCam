package com.enzo.wwcam.application

import android.app.Application
import com.enzo.wwcam.dagger.AppComponent
import com.enzo.wwcam.dagger.DaggerAppComponent
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.Logger.addLogAdapter



class WebcamApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        Logger.addLogAdapter(AndroidLogAdapter())

        appComponent = DaggerAppComponent
            .builder()
            .applicationContext(applicationContext)
            .build()
//        appComponent.inject(this)
    }
}