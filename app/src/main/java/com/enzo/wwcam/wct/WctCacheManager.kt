package com.enzo.wwcam.wct

import android.content.Context
import androidx.room.Room
import com.enzo.wwcam.database.AppDatabase
import com.enzo.wwcam.model.WebcamInfo
import javax.inject.Inject

class WctCacheManager @Inject constructor(applicationContext: Context) {

    lateinit var db: AppDatabase

    init {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    fun save(webcams: Array<WebcamInfo>) {
    }
}