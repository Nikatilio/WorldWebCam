package com.enzo.wwcam.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface NetworkCacheDao {
    @Query("SELECT * FROM networkCache")
    fun getAll(): Maybe<List<NetworkCache>>

    @Query("SELECT * FROM networkCache WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): NetworkCache

    @Insert
    fun insert(networkCache: NetworkCache): Completable

    @Delete
    fun delete(networkCache: NetworkCache)
}