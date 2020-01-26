package com.iamagamedev.trainingapp.ui.thisTraining.fragments.newExercise

import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface ICreateNewExercisePresenter : IGeneralPresenter {

    fun onAttachView(view: ICreateNewExerciseView)
    fun addExerciseToDb(sExerciseInside: Boolean, exName: String, exGroupName: String, exImageId: Int)
}