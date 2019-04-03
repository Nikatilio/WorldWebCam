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

data class WebcamImage(@JsonProperty("current") val current: WebImageUrl, @JsonProperty("daylight") val daylight: WebImageUrl, @JsonProperty("sizes") val sizes: WebImageSizes, @JsonProperty("update") val update: Long, @JsonProperty("interval") val interval: Int)

data class WebImageUrl(@JsonProperty("icon") val icon: String, @JsonProperty("thumbnail") val thumbnail: String, @JsonProperty("preview") val preview: String, @JsonProperty("toenail") val toenail: String)

data class WebImageSizes(@JsonProperty("icon") val icon: WebImageSize, @JsonProperty("thumbnail") val thumbnail: WebImageSize, @JsonProperty("preview") val preview: WebImageSize, @JsonProperty("toenail") val toenail: WebImageSize)

data class WebImageSize(@JsonProperty("width") val width: Int, @JsonProperty("height") val height: Int)

data class WebCamLocation(@JsonProperty("city") val city: String, @JsonProperty("region") val region: String, @JsonProperty("region_code") val region_code: String, @JsonProperty("country") val country: String, @JsonProperty("country_code") val country_code: String, @JsonProperty("continent") val continent: String, @JsonProperty("continent_code") val continent_code: String, @JsonProperty("latitude") val latitude: String, @JsonProperty("longitude") val longitude: String, @JsonProperty("timezone") val timezone: String, @JsonProperty("wikipedia") val wikipedia: String)