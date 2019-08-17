package com.iamagamedev.trainingapp.ui.thisTraining.fragments.exerciseChoice

import android.content.Context
import android.view.View
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.general.IGeneralPresenter

interface IExerciseChoicePresenter : IGeneralPresenter {

    fun onAttachView(view: IExerciseChoiceView)

    fun addRemoveExercise(exerciseName: String, view: View)

    fun addToTraining(trainingViewModel: TrainingViewModel, context: Context)

    fun getList(exerciseList: List<ExerciseObject>): List<ExerciseObject>
}