package com.iamagamedev.trainingapp.ui.main

import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface IMainInteractor {

    interface OnMainListener : IGeneralInteractorListener{
        fun onSuccess()
    }

    fun getTrainingsProductivity()
}