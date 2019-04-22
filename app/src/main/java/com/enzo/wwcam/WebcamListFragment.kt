package com.enzo.wwcam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enzo.wwcam.application.WebcamApplication
import com.enzo.wwcam.model.WebcamInfo
import com.enzo.wwcam.wct.WctApi
import com.orhanobut.logger.Logger
import javax.inject.Inject

class WebcamListFragment : Fragment() {

    @Inject lateinit var wctApi: WctApi

    val WEBCAM_LIST_PARAMS = 1

    private lateinit var progressBar: ProgressBar

    private lateinit var webcamAdapter: WebcamAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var model: WebcamListModel

    companion object {
        fun newInstance(): WebcamListFragment {
           return WebcamListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Logger.d("onCreate")

        (activity!!.application as WebcamApplication).appComponent.inject(this)

        model = ViewModelProviders.of(this).get(WebcamListModel::class.java)
        model.wctApi = wctApi

        model.loadLastWebcamInfo()
        model.webCamInfo.observe(this, Observer<Array<WebcamInfo>> {
            webcamAdapter.setItems(it)
            webcamAdapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_webcam_list, container, false)

        webcamAdapter = WebcamAdapter(activity) {
            val intent = Intent(activity, WebcamDetailsActivity::class.java)

            intent.putExtra("WebCamId", it.id)
            startActivity(intent)
        }

        progressBar = view.findViewById(R.id.webcamp_list_progressbar)
        progressBar.visibility = View.VISIBLE

        val recyclerView = view.findViewById(R.id.webcam_recyclerview) as RecyclerView

//        linearLayoutManager = LinearLayoutManager(activity)
//        recyclerView.layoutManager = linearLayoutManager
//        recyclerView.adapter = webcamAdapter

        gridLayoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = webcamAdapter

        val settings = view.findViewById<Button>(R.id.settings_button)
        settings.setOnClickListener{
            val intent = Intent(activity, WebcamListParamsActivity::class.java)
            startActivityForResult(intent, WEBCAM_LIST_PARAMS)
        }



//        val webcamCardView = view.findViewById(R.id.webcamCardView) as CardView


        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        progressBar.visibility = View.VISIBLE
        model.updateWebcamInfo()

    }
}