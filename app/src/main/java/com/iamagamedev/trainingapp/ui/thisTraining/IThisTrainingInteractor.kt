package com.iamagamedev.trainingapp.ui.thisTraining

import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface IThisTrainingInteractor {

    interface OnThisTrainingListener : IGeneralInteractorListener {
        fun onSuccess()

        fun getAdapterSuccess(adapter: ThisTrainingAdapter)
    }

    fun addExercise()

    fun getAdapter(listener: OnThisTrainingListener)
}