package com.iamagamedev.trainingapp.ui.training

import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface ITrainingPresenter:IGeneralPresenter {

    fun onAttachView(view: ITrainingView)
    fun deleteTraining(training: TrainingObject)
    fun startTraining()
}