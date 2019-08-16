package com.iamagamedev.trainingapp.ui.training

import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface ITrainingInteractor {

    interface OnTrainingListener : IGeneralInteractorListener {
        fun onSuccess()
        fun onDeleteSuccess(training: TrainingObject)
        fun onSuccessStartThisTrainingActivity()
    }

    fun deleteTraining(training: TrainingObject, listener: OnTrainingListener)
    fun startTraining(listener: OnTrainingListener)
}