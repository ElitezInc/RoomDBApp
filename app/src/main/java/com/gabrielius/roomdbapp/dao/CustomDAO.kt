package com.gabrielius.roomdbapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customEntity: CustomEntity)

    @Update
    suspend fun update(customEntity: CustomEntity)

    @Query("DELETE FROM custom_table WHERE Id = :id")
    suspend fun delete(id : Int?)

    @Query("DELETE FROM custom_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM custom_table ORDER BY Id ASC")
    suspend fun getAll() : List<CustomEntity>
}