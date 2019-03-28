package com.enzo.wwcam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WebcamDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webcam_details)

        val index = intent.extras?.getString("WebCamId") ?: "empty"

        val arguments = Bundle()
        arguments.putString("WebCamId", index)

        val webCamDetailsFragment = WebcamDetailsFragment.newInstance()
        webCamDetailsFragment.arguments = arguments

        supportFragmentManager
            .beginTransaction()
            .add(R.id.webcam_details_container, webCamDetailsFragment, "WebcamDetailsFragment")
            .commit()
    }
}