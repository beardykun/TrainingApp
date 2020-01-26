package com.iamagamedev.trainingapp.ui.thisTraining.fragments.newExercise

import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface ICreateNewExerciseInteractor {

    interface OnCreateNewExerciseListener : IGeneralInteractorListener {
        fun onSuccess()
        fun onSuccessDBInsert(exObject: ExerciseObject)
    }

    fun addExerciseToDb(isExerciseInside: Boolean, exName: String, exGroupName: String, exImageId: Int,
                        listener: OnCreateNewExerciseListener)
}