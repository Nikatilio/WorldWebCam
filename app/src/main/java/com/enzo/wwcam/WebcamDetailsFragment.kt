package com.enzo.wwcam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView
import com.enzo.wwcam.application.WebcamApplication
import com.enzo.wwcam.wct.WctApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_webcam_details.*
import javax.inject.Inject


class WebcamDetailsFragment: Fragment() {

    @Inject
    lateinit var wctApi: WctApi

    companion object {
        fun newInstance(): WebcamDetailsFragment {
            return WebcamDetailsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity!!.application as WebcamApplication).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_webcam_details, container, false)

        val indexTextView = view.findViewById<TextView>(R.id.webcamTitle)

        val bundle = this.arguments
        if (bundle != null) {
            val index = bundle.getString("WebCamId", "empty id")

            wctApi.loadWebcam(index) {
                webcamTitle.text = it.title
                Picasso.get().load(it.image?.current?.preview).into(imageView)
                println("----------------------------------READ ${it.title} from Database!")
            }

            indexTextView.text = "Selected webcam $index"
        }



        return view
    }

    override fun onResume() {
        super.onResume()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://api.lookr.com/embed/player/1064226293/day")
    }
}