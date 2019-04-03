package com.enzo.wwcam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView


class WebcamDetailsFragment: Fragment() {

    companion object {
        fun newInstance(): WebcamDetailsFragment {
            return WebcamDetailsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_webcam_details, container, false)

        val indexTextView = view.findViewById<TextView>(R.id.webcam_index)

        val bundle = this.arguments
        if (bundle != null) {
            val index = bundle.getString("WebCamId", "empty id")
            indexTextView.text = "Selected webcam $index"
        }



        return view
    }
}