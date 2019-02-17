package com.iamagamedev.trainingapp.ui.splash

import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface ISplashInteractor {

    interface OnSplashListener : IGeneralInteractorListener {
        fun onSuccess()
    }
    fun databaseLoading(listener: OnSplashListener)
}