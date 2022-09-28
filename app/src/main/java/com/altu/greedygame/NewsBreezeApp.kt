package com.altu.greedygame

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsBreezeApp:Application() {
    init {
        instance=this
    }
    companion object
    {
        lateinit var instance:NewsBreezeApp
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}