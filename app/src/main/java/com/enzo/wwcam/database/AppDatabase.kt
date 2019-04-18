package com.enzo.wwcam.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Field::class, NetworkCache::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun fieldDao(): FieldDao
    abstract fun networkCacheDao(): NetworkCacheDao
}