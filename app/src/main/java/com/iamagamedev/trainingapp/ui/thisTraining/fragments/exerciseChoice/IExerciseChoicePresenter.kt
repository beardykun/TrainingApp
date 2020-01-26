package com.iamagamedev.trainingapp.ui.thisTraining.fragments.exerciseChoice

import android.view.View
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface IExerciseChoicePresenter : IGeneralPresenter {

    fun onAttachView(view: IExerciseChoiceView)

    fun addRemoveExercise(exerciseName: String, view: View)

    fun getNewTrainingList(): String

    fun getList(exerciseList: List<ExerciseObject>): List<ExerciseObject>
}