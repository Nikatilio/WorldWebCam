package com.enzo.wwcam.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WebcamInfoCache(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "jsonString") val jsonString: String
)