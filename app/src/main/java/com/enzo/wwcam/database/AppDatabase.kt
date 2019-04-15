package com.enzo.wwcam.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Field::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun fieldDao(): FieldDao
}