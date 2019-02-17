package com.iamagamedev.trainingapp.ui.createNewExercise

import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface ICreateNewExercisePresenter: IGeneralPresenter {

    fun onAttachView(view: ICreateNewExerciseView)
    fun addExerciseToDb()
    fun addExerciseGroupAndImage(position: Int, muscleGroup: String)
    fun addExerciseName(name: String)
}