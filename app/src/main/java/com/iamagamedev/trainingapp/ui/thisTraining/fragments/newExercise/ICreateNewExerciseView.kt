package com.iamagamedev.trainingapp.ui.thisTraining.fragments.newExercise

import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.general.IGeneralView

interface ICreateNewExerciseView : IGeneralView {

    fun addExToDatabase(exObject: ExerciseObject)
}