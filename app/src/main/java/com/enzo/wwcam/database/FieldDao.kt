package com.enzo.wwcam.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FieldDao {
    @Query("SELECT * FROM field")
    fun getAll(): List<Field>

    @Query("SELECT * FROM field WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Field

    @Insert
    fun insertAll(vararg fields: Field)

    @Delete
    fun delete(field: Field)
}