package com.gabrielius.roomdbapp.model

class CustomModel
{
    var id : Int? = null
    var name : String? = null

    constructor(name : String)
    {
        this.name = name
    }

    constructor(id : Int, name : String)
    {
        this.id = id
        this.name = name
    }
}