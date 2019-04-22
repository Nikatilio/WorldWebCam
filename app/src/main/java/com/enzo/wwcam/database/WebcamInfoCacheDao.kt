package com.enzo.wwcam.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface WebcamInfoCacheDao {
    @Query("SELECT * FROM webcamInfoCache")
    fun getAll(): Maybe<List<WebcamInfoCache>>

    @Query("SELECT * FROM webcamInfoCache WHERE id LIKE :id LIMIT 1")
    fun findById(id: String): Maybe<WebcamInfoCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(networkCache: WebcamInfoCache): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(networkCache: List<WebcamInfoCache>): Completable

    @Delete
    fun delete(networkCache: WebcamInfoCache)
}