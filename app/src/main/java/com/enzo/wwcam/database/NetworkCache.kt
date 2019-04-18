package com.enzo.wwcam.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NetworkCache(
    @PrimaryKey var uid: Int,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "value") var value: String?
)