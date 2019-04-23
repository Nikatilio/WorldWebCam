package com.enzo.wwcam.wct

import android.content.Context
import androidx.annotation.MainThread
import androidx.room.Room
import com.enzo.wwcam.database.AppDatabase
import com.enzo.wwcam.database.NetworkCache
import com.enzo.wwcam.database.WebcamInfoCache
import com.enzo.wwcam.model.WebcamInfo
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WctCacheManager @Inject constructor(applicationContext: Context) {

    lateinit var db: AppDatabase
    lateinit var objectMapper: ObjectMapper

    init {
        objectMapper = ObjectMapper()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "world-webcam"
        ).build()
    }

    fun save(webcams: String?) {

        db.networkCacheDao().insert(NetworkCache(0, "webcams", webcams)).subscribeOn(Schedulers.io()).subscribeBy(
            onComplete = {
                println("Network cache updated")
            },
            onError = {
                println("Network cache update error")
                println(it)
            }
        )
    }

    fun save(webcams: Array<WebcamInfo>) {

        val webcamJsonStrings = mutableListOf<WebcamInfoCache>()
        webcams.forEach {
            webcamJsonStrings.add(WebcamInfoCache(it.id, objectMapper.writeValueAsString(it)))
        }

        db.webcamInfoCacheDao().insertAll(webcamJsonStrings).subscribeOn(Schedulers.io()).subscribeBy(
            onComplete = {
                println("Loaded webcamInfo saved to DB")
            },
            onError = {
                println("WebcamInfo can't be inserted into DB")
            }
        )
    }

    fun get(): Maybe<List<NetworkCache>> {
        return db.networkCacheDao().getAll()
    }

    fun getWebcamCache(id: String, callback: (WebcamInfo) -> Unit) {
        db.webcamInfoCacheDao().findById(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            callback(objectMapper.readValue(it.jsonString, WebcamInfo::class.java))
        }
    }
}