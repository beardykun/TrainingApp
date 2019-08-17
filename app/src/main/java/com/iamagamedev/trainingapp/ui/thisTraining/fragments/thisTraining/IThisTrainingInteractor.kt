package com.iamagamedev.trainingapp.ui.thisTraining.fragments.thisTraining

import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface IThisTrainingInteractor {

    interface OnThisTrainingListener : IGeneralInteractorListener {
        fun onSuccess()

        fun getAdapterSuccess(adapter: ThisTrainingAdapter)
    }

    fun getAdapter(training: TrainingObject, listener: OnThisTrainingListener)
}