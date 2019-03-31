package com.enzo.wwcam.wct

import com.enzo.wwcam.model.WebcamInfo
import com.enzo.wwcam.wct.params.WctCountry
import javax.inject.Singleton

@Singleton
interface WctApi {

    val categories: ArrayList<String>

    val countries: ArrayList<WctCountry>

    val regions: ArrayList<String>

    val continents: ArrayList<String>

    val properties: ArrayList<String>

    val fieldsToShow: ArrayList<String>

    val orderParams: ArrayList<String>

    val webcamFields: ArrayList<String>

    val languages: ArrayList<String>

    fun prepare()

    fun filterByCountry(country: String)

    fun load(callback: (Array<WebcamInfo>) -> Unit)

    fun setSelectedCountries(countryIndexes: Array<Int>)
}