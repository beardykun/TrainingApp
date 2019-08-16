package com.iamagamedev.trainingapp.ui.createNewExercise

import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface ICreateNewExercisePresenter: IGeneralPresenter {

    fun onAttachView(view: ICreateNewExerciseView)
    fun addExerciseToDb(exerciseObject: ExerciseObject?, exName: String, exGroupName: String, exImageId: Int)
}