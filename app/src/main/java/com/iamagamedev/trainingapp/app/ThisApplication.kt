package com.iamagamedev.trainingapp.app

import android.app.Application

class ThisApplication : Application() {

    companion object {
        lateinit var instance: ThisApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}