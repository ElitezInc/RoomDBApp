package com.gabrielius.roomdbapp.view

import com.gabrielius.roomdbapp.model.CustomModel

interface CustomListeners {

    fun onUpdate(item : CustomModel, position : Int)

    fun onDelete(item : CustomModel, position : Int)

}