package com.gabrielius.roomdbapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielius.roomdbapp.dao.CustomEntity
import com.gabrielius.roomdbapp.model.CustomModel
import com.gabrielius.roomdbapp.repository.DBRepository
import com.gabrielius.roomdbapp.utils.ConvertList
import com.gabrielius.roomdbapp.utils.Coroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(baseRepository : DBRepository) : ViewModel()
{
    val repository : DBRepository = baseRepository
    val liveList : MutableLiveData<List<CustomEntity>> = MutableLiveData()
    val liveUpdate : MutableLiveData<CustomModel> = MutableLiveData<CustomModel>()

    init {
        viewModelScope.launch {
            liveList.postValue(repository.getAll())
        }
    }

    fun getInstance() : String
    {
        return this.toString()
    }

    fun getRepositoryInstance() : String
    {
        return repository.giveRepository()
    }

    fun setUpdate(item : CustomModel)
    {
        liveUpdate.value = item
    }

    fun getUpdate() : LiveData<CustomModel>
    {
        return liveUpdate
    }

    fun insertItem(item : CustomModel)
    {
        viewModelScope.launch {
            repository.insert(
                ConvertList.toEntity(item)
            )
            liveList.postValue(repository.getAll())
        }
    }

    fun updateItem()
    {
        liveUpdate.value?.let {
            viewModelScope.launch {
                repository.update(
                    ConvertList.toEntity(it)
                )
                liveList.postValue(repository.getAll())
            }
        }
    }

    fun deleteItem(item : CustomModel)
    {
        viewModelScope.launch {
            repository.delete(
                ConvertList.toEntity(item)
            )
            liveList.postValue(repository.getAll())
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
            liveList.postValue(repository.getAll())
        }
    }

    fun getItems() : LiveData<MutableList<CustomModel>>
    {
        return ConvertList.toLiveDataListModel(liveList)
    }
}