package com.iamagamedev.trainingapp.ui.splash

import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface ISplashPresenter : IGeneralPresenter{

    fun onAttachView(view: ISplashView)

    fun loadDB()
}