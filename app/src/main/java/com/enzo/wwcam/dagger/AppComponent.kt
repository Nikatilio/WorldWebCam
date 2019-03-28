package com.enzo.wwcam.dagger

import android.app.Application
import com.enzo.wwcam.MainActivity
import com.enzo.wwcam.WebcamDetailsFragment
import com.enzo.wwcam.WebcamListFragment
import com.enzo.wwcam.WebcamListParamsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: Application)

    fun inject(target: MainActivity)

    fun inject(target: WebcamListParamsFragment)

    fun inject(target: WebcamListFragment)
}