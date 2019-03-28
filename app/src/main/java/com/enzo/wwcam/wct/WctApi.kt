package com.enzo.wwcam.wct

import com.enzo.wwcam.model.WebcamInfo
import javax.inject.Singleton

@Singleton
interface WctApi {

    val categories: ArrayList<String>

    val countries: ArrayList<String>

    val regions: ArrayList<String>

    val continents: ArrayList<String>

    val properties: ArrayList<String>

    fun prepare()

    fun filterByCountry(country: String)

    fun load(callback: (Array<WebcamInfo>) -> Unit)
}