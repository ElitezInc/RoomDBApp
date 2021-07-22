package com.gabrielius.roomdbapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gabrielius.roomdbapp.dao.CustomEntity
import com.gabrielius.roomdbapp.model.CustomModel

object ConvertList {
    private fun toListModel(customEntity: List<CustomEntity>) : MutableList<CustomModel> {
        val itemList : MutableList<CustomModel> = mutableListOf<CustomModel>()
        customEntity.map {
            itemList.add(
                CustomModel(it.id?:0, it.name?:"")
            )
        }
        return itemList
    }

    fun toLiveDataListModel(localList : LiveData<List<CustomEntity>>) : LiveData<MutableList<CustomModel>> {
        return Transformations.map(
            localList
        ) { listEntity ->
            toListModel(listEntity)
        }
    }

    fun toEntity(customModel: CustomModel) : CustomEntity {
        return when(customModel.id) {
            null -> {
                CustomEntity(
                    customModel.name?:"",
                )
            }
            else -> {
                CustomEntity(
                    customModel.id!!,
                    customModel.name?:"",
                )
            }
        }
    }
}