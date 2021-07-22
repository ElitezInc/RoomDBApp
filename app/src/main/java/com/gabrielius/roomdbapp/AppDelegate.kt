package com.gabrielius.roomdbapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppDelegate : Application()
{
    override fun onCreate()
    {
        super.onCreate()
    }
}