package com.enzo.wwcam

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.enzo.wwcam.application.WebcamApplication
import com.enzo.wwcam.dagger.DaggerAppComponent
import com.enzo.wwcam.wct.WctApi
import dagger.android.DaggerApplication
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var wctApi: WctApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as WebcamApplication).appComponent.inject(this)

        wctApi.prepare()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.mainContainer, WebcamListFragment.newInstance(), "WebcamListFragment")
            .commit()
    }
}
