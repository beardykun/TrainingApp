package com.iamagamedev.trainingapp.ui.thisTraining

import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface IThisTrainingPresenter: IGeneralPresenter {

    fun onAttachView(view: IThisTrainingView)

    fun getAdapter(training: TrainingObject)
}