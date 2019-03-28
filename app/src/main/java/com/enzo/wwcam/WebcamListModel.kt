package com.enzo.wwcam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enzo.wwcam.model.WebcamInfo
import com.enzo.wwcam.wct.WctApi

class WebcamListModel: ViewModel() {

//    data class WebCamResult(val offset: Int, val limit: Int, val total: Int, val webcams: Array<WebCamInfo>)
//
//    data class WebCamResponse(val status: String, val result: WebCamResult?, val message: String?)

    lateinit var wctApi: WctApi

    var webCamInfo = MutableLiveData<Array<WebcamInfo>>()

    fun loadWebCamInfo1() {
//        Fuel.get("https://webcamstravel.p.rapidapi.com/webcams/list/limit=20,0")
//            .header("X-RapidAPI-Key", "50efcd2654msh183733afe64a7cbp14bc4ejsn0d2ef8778562")
//            .responseObject<WebCamResponse> { request, response, result ->
//                if (result.get().status == "OK") {
//                    webCamInfo.postValue(result.get().result?.webcams)
//                } else {
//                    println(result.get().message)
//                }
//
//            }

    }

    fun loadWebCamInfo() {
//        Fuel.get("https://webcamstravel.p.rapidapi.com/webcams/list/limit=20", listOf("lang" to "en", "show" to "webcams"))
//            .header("X-RapidAPI-Key", "50efcd2654msh183733afe64a7cbp14bc4ejsn0d2ef8778562")
//            .responseString { request, response, result ->
//                println(request.body)
//                println(result.get())
//            }
    }

    fun updateWebcamInfo() {

        wctApi.load {
            webCamInfo.postValue(it)
        }
    }
}