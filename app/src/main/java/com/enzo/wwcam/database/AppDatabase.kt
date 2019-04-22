package com.enzo.wwcam.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.enzo.wwcam.model.WebcamInfo

@Database(entities = [Field::class, NetworkCache::class, WebcamInfoCache::class], version = 4)
abstract class AppDatabase: RoomDatabase() {
    abstract fun fieldDao(): FieldDao
    abstract fun networkCacheDao(): NetworkCacheDao
    abstract fun webcamInfoCacheDao(): WebcamInfoCacheDao
}