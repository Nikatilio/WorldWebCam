package com.enzo.wwcam

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.enzo.wwcam.model.WebcamInfo
import kotlinx.android.synthetic.main.webcam_list_item.view.*



class WebcamAdapter(private val activity: FragmentActivity?, private val webCamSelectListener: (String) -> Unit) : RecyclerView.Adapter<WebcamAdapter.WebCamHolder>() {

    private val data: MutableList<WebcamInfo> = ArrayList()

    fun setItems(list: Array<WebcamInfo>) {
        data.clear()
        data.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebcamAdapter.WebCamHolder {
        val inflatedView = parent.inflate(R.layout.webcam_list_item, false)
        return WebCamHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: WebcamAdapter.WebCamHolder, position: Int) {
        val dataItem = data[position]
        holder.bindData(dataItem, webCamSelectListener)
    }

    inner class WebCamHolder (v: View): RecyclerView.ViewHolder(v) {

        private var view: View = v
        private lateinit var webCamInfo: WebcamInfo

        fun bindData(webCamInfo: WebcamInfo, webCamSelectListener: (String) -> Unit) {
            this.webCamInfo = webCamInfo
            view.webcam_name.text = webCamInfo.title
            view.setOnClickListener { webCamSelectListener(webCamInfo.id) }
        }
    }
}