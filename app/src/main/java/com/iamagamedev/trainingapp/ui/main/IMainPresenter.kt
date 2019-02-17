package com.iamagamedev.trainingapp.ui.main

import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface IMainPresenter: IGeneralPresenter {

    fun onAttachView(view: IMainView)

    fun getTrainingsProductivity()
}