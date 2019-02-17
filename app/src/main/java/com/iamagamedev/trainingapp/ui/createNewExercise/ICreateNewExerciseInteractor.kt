package com.iamagamedev.trainingapp.ui.createNewExercise

import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface ICreateNewExerciseInteractor {

    interface OnCreateNewExerciseListener : IGeneralInteractorListener {
        fun onSuccess()
    }

    fun addValidateFields(listener: OnCreateNewExerciseListener)
    fun addExerciseGroupAndImage(position: Int, muscleGroup: String, listener: OnCreateNewExerciseListener)
    fun addExerciseName(name: String, listener: OnCreateNewExerciseListener)
}