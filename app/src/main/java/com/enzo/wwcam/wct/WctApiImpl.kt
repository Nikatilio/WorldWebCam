package com.enzo.wwcam.wct

import com.enzo.wwcam.model.WebcamInfo
import com.enzo.wwcam.model.WebcamResponse
import com.enzo.wwcam.network.NetworkManager
import com.enzo.wwcam.wct.params.WctOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class WctApiImpl @Inject constructor(val networkManager: NetworkManager): WctApi {
    val url = "https://webcamstravel.p.rapidapi.com/webcams/list/"

    override var categories = ArrayList<String>()
    override var countries = ArrayList<String>()
    override var regions = ArrayList<String>()
    override var continents = ArrayList<String>()
    override var properties = ArrayList<String>()

    var excludedIds = ArrayList<String>()
    var order = WctOrder.DESC
    var limit = 10

    override fun prepare() {
        loadValues()
    }

    fun setExcludedWebcamIds(excludedIds: ArrayList<String>) {

    }

    fun getByArea(ne_lat: Double, ne_lng: Double, sw_lat: Double, sw_lng: Double) {

    }

    fun getByContinent(vararg continent: String) {
    }

    fun getByCountry(vararg countries: String) {
    }

    fun getNearby(lat: Double, lng: Double, radius: Int) {
    }

    fun getByRegion(vararg regions: String) {
    }

    fun getByWebcam(vararg webcamIds: String) {
    }

    fun getWebcamMap(ne_lat: Double, ne_lng: Double, sw_lat: Double, sw_lng: Double, zoom: Int) {
    }

    override fun load(callback: (Array<WebcamInfo>) -> Unit) {
        networkManager.build(url, "/webcams/list/category=building", object: Callback<WebcamResponse> {
            override fun onFailure(call: Call<WebcamResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<WebcamResponse>, response: Response<WebcamResponse>) {
                callback(response.body()?.result?.webcams!!)
            }
        })

    }

    override fun filterByCountry(country: String) {
//        networkManager.build(url, "/country=" + country, object: Callback<WebcamResponse> {
//            override fun onFailure(call: Call<WebcamResponse>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<WebcamResponse>, response: Response<WebcamResponse>) {
//
//            }
//        })
    }

    private fun loadValues() {
        networkManager.get(url, arrayOf("categories", "properties", "continents", "countries", "regions"), object: Callback<WebcamResponse> {
            override fun onFailure(call: Call<WebcamResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<WebcamResponse>, response: Response<WebcamResponse>) {
                if(response.isSuccessful) {

                    val propertiesAll = ArrayList<ArrayList<String>>()

                    val result = response.body()?.result
                    result?.categories?.let {
                        for (category in it) {
                            categories.add(category.name)
                        }
                        propertiesAll.add(categories)
                    }
                    result?.properties?.let {
                        for (property in it) {
                            properties.add(property.name)
                        }
                        propertiesAll.add(properties)
                    }
                    result?.continents?.let {
                        for (continent in it) {
                            continents.add(continent.name)
                        }
                    }
                    result?.countries?.let {
                        for (country in it) {
                            countries.add(country.name)
                        }
                    }
                    result?.regions?.let {
                        for (region in it) {
                            regions.add(region.name)
                        }
                    }

                } else {
                    error(response.errorBody().toString())
                }
            }
        })
    }
}