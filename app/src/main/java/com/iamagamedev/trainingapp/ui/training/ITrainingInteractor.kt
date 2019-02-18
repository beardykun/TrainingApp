package com.iamagamedev.trainingapp.ui.training

import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface ITrainingInteractor {

    interface OnTrainingListener : IGeneralInteractorListener {
        fun onSuccess()
        fun onDeleteSuccess()
        fun onSuccessStartThisTrainingActivity()
    }

    fun updateSet(newItem: String, listener: OnTrainingListener)
    fun deleteTraining(name: String, listener: OnTrainingListener)
    fun startTraining(listener: OnTrainingListener)
}