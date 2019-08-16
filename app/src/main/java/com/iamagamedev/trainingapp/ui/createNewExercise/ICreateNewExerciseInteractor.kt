package com.iamagamedev.trainingapp.ui.createNewExercise

import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface ICreateNewExerciseInteractor {

    interface OnCreateNewExerciseListener : IGeneralInteractorListener {
        fun onSuccess()
        fun onSuccessDBInsert()
    }

    fun addExerciseToDb(exObject: ExerciseObject?, exName:String, exGroupName: String, exImageId: Int, listener: OnCreateNewExerciseListener)
}