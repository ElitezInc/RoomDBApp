package com.gabrielius.roomdbapp.repository

import androidx.lifecycle.LiveData
import com.gabrielius.roomdbapp.dao.CustomDAO
import com.gabrielius.roomdbapp.dao.CustomEntity
import javax.inject.Inject

class DBRepository
{
    val customDao : CustomDAO

    @Inject
    constructor(customDao : CustomDAO)
    {
        this.customDao = customDao
    }

    fun giveRepository(): String
    {
        return this.toString()
    }

    //region CRUD Operation
    suspend fun insert(customEntity : CustomEntity)
    {
        customDao.insert(customEntity)
    }

    suspend fun update(customEntity : CustomEntity)
    {
        customDao.update(customEntity)
    }

    suspend fun delete(customEntity: CustomEntity)
    {
        customDao.delete(customEntity.id)
    }

    suspend fun deleteAll()
    {
        customDao.deleteAll()
    }

    suspend fun getAll() : List<CustomEntity>
    {
        return customDao.getAll()
    }
    //endregion
}