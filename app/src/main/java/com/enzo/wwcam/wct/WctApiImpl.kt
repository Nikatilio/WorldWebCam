package com.enzo.wwcam.wct

import android.annotation.SuppressLint
import androidx.room.Room
import com.enzo.wwcam.database.AppDatabase
import com.enzo.wwcam.model.WebcamInfo
import com.enzo.wwcam.model.WebcamResponse
import com.enzo.wwcam.network.NetworkManager
import com.enzo.wwcam.wct.params.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class WctApiImpl @Inject constructor(val networkManager: NetworkManager, val cacheManager: WctCacheManager): WctApi {
    val url = "https://webcamstravel.p.rapidapi.com/webcams/list/"

    override var continents = ArrayList<WctItem>()
    override var countries = ArrayList<WctItem>()
    override var regions = ArrayList<WctItem>()
    override var categories = ArrayList<WctItem>()
    override var properties = ArrayList<WctItem>()
    override val fieldsToShow = ArrayList<String>()
    override val orderParams = ArrayList<String>()
    override val webcamFields = ArrayList<String>()
    override val languages = ArrayList<String>()

    val selectedCountries = ArrayList<WctItem>()
    val selectedContinents = ArrayList<WctItem>()
    val selectedRegions = ArrayList<WctItem>()
    val selectedCategories = ArrayList<WctItem>()
    val selectedProperties = ArrayList<WctItem>()



    var excludedIds = ArrayList<String>()
    var order = WctOrder.DESC
    var limit = 10

    init {
        WctShow.values().forEach {
            fieldsToShow.add(it.value)
        }

        WctOrder.values().forEach {
            orderParams.add(it.value)
        }

        WctWebcamFields.values().forEach {
            webcamFields.add(it.value)
        }

        WctLanguage.values().forEach {
            languages.add(it.value)
        }
    }

    override fun prepare() {
        loadValues()
    }

    override fun setSelectedCountries(countryIndexes: Array<Int>) {
        selectedCountries.clear()
        countryIndexes.forEach {
            selectedCountries.add(countries[it])
        }
    }

    override fun setSelectedContinents(continentIndexes: Array<Int>) {
        selectedContinents.clear()
        continentIndexes.forEach { selectedContinents.add(continents[it]) }
    }

    override fun setSelectedRegions(regionIndexes: Array<Int>) {
        selectedRegions.clear()
        regionIndexes.forEach { selectedRegions.add(regions[it]) }
    }

    override fun setSelectedCategories(categoryIndexes: Array<Int>) {
        selectedCategories.clear()
        categoryIndexes.forEach { selectedCategories.add(regions[it]) }
    }

    override fun setSelectedProperties(propertyIndexes: Array<Int>) {
        selectedProperties.clear()
        propertyIndexes.forEach { selectedProperties.add(properties[it]) }
    }

    override fun load(callback: (Array<WebcamInfo>) -> Unit) {

        var params = ""
        if (!selectedContinents.isEmpty()) {
            params += "/continent=" + selectedContinents.joinToString(";") {
                it.id
            }
        }
        if (!selectedCountries.isEmpty()) {
            params += "/country=" + selectedCountries.joinToString(";") {
                it.id
            }
        }
        if (!selectedRegions.isEmpty()) {
            params += "/region=" + selectedRegions.joinToString(";") {
                it.id
            }
        }
        if (!selectedCategories.isEmpty()) {
            params += "/category=" + selectedCategories.joinToString(";") {
                it.id
            }
        }
        if (!selectedProperties.isEmpty()) {
            params += "/property=" + selectedProperties.joinToString(";") {
                it.id
            }
        }

        if (params.isEmpty()) {
            System.err.println("Request parameters are empty")
            return
        }

        networkManager.build(url, "/webcams/list$params?show=webcams:image,location;categories", object: Callback<WebcamResponse> {
            override fun onFailure(call: Call<WebcamResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<WebcamResponse>, response: Response<WebcamResponse>) {
                callback(response.body()?.result?.webcams!!)

                cacheManager.save(response.raw().request().url().toString())
            }
        })
    }

    @SuppressLint("CheckResult")
    override fun loadLastCache(callback: (Array<WebcamInfo>) -> Unit) {
        cacheManager.get().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.isNotEmpty()) {
                networkManager.get(it[0].value!!, object: Callback<WebcamResponse> {
                    override fun onFailure(call: Call<WebcamResponse>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<WebcamResponse>, response: Response<WebcamResponse>) {
                        if(response.isSuccessful) {

                            cacheManager.save(response.body()?.result?.webcams!!)

                            callback(response.body()?.result?.webcams!!)

                        } else {
                            error(response.errorBody().toString())
                        }
                    }
                })
            }
        }
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
                            categories.add(WctItem(category.id, category.name))
                        }
                        //propertiesAll.add(categories)
                    }
                    result?.properties?.let {
                        for (property in it) {
                            properties.add(WctItem(property.id, property.name))
                        }
                        //propertiesAll.add(properties)
                    }
                    result?.continents?.let {
                        for (continent in it) {
                            continents.add(WctItem(continent.id, continent.name))
                        }
                    }
                    result?.countries?.let {
                        for (country in it) {
                            countries.add(WctItem(country.id, country.name))

                        }
                    }
                    result?.regions?.let {
                        for (region in it) {
                            regions.add(WctItem(region.id, region.name))
                        }
                    }

                } else {
                    error(response.errorBody().toString())
                }
            }
        })
    }

    override fun loadWebcam(id: String, callback: (WebcamInfo) -> Unit) {
        cacheManager.getWebcamCache(id, callback)
    }
}