package com.iamagamedev.trainingapp.ui.training

import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface ITrainingPresenter:IGeneralPresenter {

    fun onAttachView(view: ITrainingView)

    fun updateSet(newItem: String)
    fun deleteTraining(name: String)
    fun startTraining()
    fun getAdapter()
}