package com.enzo.wwcam.model

import com.fasterxml.jackson.annotation.JsonProperty

data class WebcamResponse(@JsonProperty("status") val status: String, @JsonProperty("result") val result: WebcamResult?, @JsonProperty("message") val message: String?)

data class WebcamResult(
    @JsonProperty("offset") val offset: Int,
    @JsonProperty("limit") val limit: Int,
    @JsonProperty("total") val total: Int,
    @JsonProperty("webcams") val webcams: Array<WebcamInfo>?,
    @JsonProperty("categories") val categories: Array<ShowParameterInfo>?,
    @JsonProperty("properties") val properties: Array<ShowParameterInfo>?,
    @JsonProperty("continents") val continents: Array<ShowParameterInfo>?,
    @JsonProperty("countries") val countries: Array<ShowParameterInfo>?,
    @JsonProperty("regions") val regions: Array<ShowParameterInfo>?)

data class WebcamInfo(
    @JsonProperty("id") val id: String,
    @JsonProperty("status") val status: String,
    @JsonProperty("title") val title: String,
    @JsonProperty("image") val image: WebcamImage?,
    @JsonProperty("location") val location: WebCamLocation?)

data class ShowParameterInfo(@JsonProperty("id") val id: String, @JsonProperty("name") val name: String, @JsonProperty("count") val count: Int)

data class WebcamImage(val current: WebImageUrl, val daylight: WebImageUrl, val sizes: WebImageSizes, val update: Long, val interval: Int)

data class WebImageUrl(val icon: String, val thumbnail: String, val preview: String, val toenail: String)

data class WebImageSizes(val icon: WebImageSize, val thumbnail: WebImageSize, val preview: WebImageSize, val toenail: WebImageSize)

data class WebImageSize(val width: Int, val height: Int)

data class WebCamLocation(val city: String, val region: String, val region_code: String, val country: String, val country_code: String, val continent: String, val continent_code: String, val latitude: String, val longitude: String, val timezone: String, val wikipedia: String)