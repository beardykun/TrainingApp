package com.iamagamedev.trainingapp.ui.exercises

import android.view.View
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface IExerciseChoicePresenter : IGeneralPresenter {

    fun onAttachView(view: IExerciseChoiceView)

    fun addRemoveExercise(exerciseName: String, view: View)

    fun addToTraining(trainingViewModel: TrainingViewModel, exerciseChoiceActivity: ExerciseChoiceActivity)

    fun createExercise()
}