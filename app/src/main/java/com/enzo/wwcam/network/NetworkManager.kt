package com.enzo.wwcam.network

import com.enzo.wwcam.model.WebcamResponse
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.Callback
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.*

class NetworkManager @Inject constructor() {

    interface WebcamList {

        @Headers(
            "X-RapidAPI-Key: 50efcd2654msh183733afe64a7cbp14bc4ejsn0d2ef8778562"
        )
        @GET
        fun getList(@Url url: String): Call<WebcamResponse>
    }

    interface ParametersList {
        @Headers(
            "X-RapidAPI-Key: 50efcd2654msh183733afe64a7cbp14bc4ejsn0d2ef8778562"
        )
        @GET("/webcams/list/limit=10,0")
        fun getList(@Query("show") order: String) : Call<WebcamResponse>
    }

    fun build(baseUrl: String, parameters: String, callback: Callback<WebcamResponse>) {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        val webcamList = retrofit.create<WebcamList>(WebcamList::class.java)
        var webcamResponse = webcamList.getList(parameters)
        webcamResponse.enqueue(callback)
    }

    fun get(baseUrl: String, parameters: Array<String>, callback: Callback<WebcamResponse>) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        val webcamList = retrofit.create<ParametersList>(ParametersList::class.java)

        var webcamResponse = webcamList.getList(parameters.joinToString(";"))
        webcamResponse.enqueue(callback)
    }

    fun get(fullUrl: String, callback: Callback<WebcamResponse>) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(fullUrl)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        val webcamList = retrofit.create<WebcamList>(WebcamList::class.java)
        var webcamResponse = webcamList.getList("")
        webcamResponse.enqueue(callback)
    }
}