package com.iamagamedev.trainingapp.ui.training

import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface ITrainingPresenter:IGeneralPresenter {

    fun onAttachView(view: ITrainingView)

    fun updateSet(viewModel: TrainingViewModel, newItem: String)
    fun deleteTraining(name: String)
    fun startTraining()
}