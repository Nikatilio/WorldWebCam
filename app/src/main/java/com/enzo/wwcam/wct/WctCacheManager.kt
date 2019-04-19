package com.enzo.wwcam.wct

import android.content.Context
import androidx.room.Room
import com.enzo.wwcam.database.AppDatabase
import com.enzo.wwcam.database.NetworkCache
import com.enzo.wwcam.model.WebcamInfo
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WctCacheManager @Inject constructor(applicationContext: Context) {

    lateinit var db: AppDatabase

    init {
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

    fun get(): Maybe<List<NetworkCache>> {
        return db.networkCacheDao().getAll()
    }
}