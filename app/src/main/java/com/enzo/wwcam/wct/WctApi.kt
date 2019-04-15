package com.enzo.wwcam.wct

import com.enzo.wwcam.model.WebcamInfo
import com.enzo.wwcam.wct.params.WctItem
import javax.inject.Singleton

@Singleton
interface WctApi {

    val continents: ArrayList<WctItem>
    val countries: ArrayList<WctItem>
    val regions: ArrayList<WctItem>
    val categories: ArrayList<WctItem>
    val properties: ArrayList<WctItem>
    val fieldsToShow: ArrayList<String>
    val orderParams: ArrayList<String>
    val webcamFields: ArrayList<String>
    val languages: ArrayList<String>

    fun prepare()

    fun load(callback: (Array<WebcamInfo>) -> Unit)
    fun loadLastCache(callback: (Array<WebcamInfo>) -> Unit)

    fun setSelectedContinents(countryIndexes: Array<Int>)
    fun setSelectedCountries(continentIndexes: Array<Int>)
    fun setSelectedRegions(regionIndexes: Array<Int>)
    fun setSelectedCategories(categoryIndexes: Array<Int>)
    fun setSelectedProperties(propertyIndexes: Array<Int>)
}