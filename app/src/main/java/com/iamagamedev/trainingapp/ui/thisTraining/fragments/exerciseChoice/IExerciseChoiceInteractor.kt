package com.iamagamedev.trainingapp.ui.thisTraining.fragments.exerciseChoice

import android.content.Context
import android.view.View
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface IExerciseChoiceInteractor {

    interface OnExerciseChoiceListener : IGeneralInteractorListener {
        fun onSuccess()

        fun onSuccessAddItem()
    }

    fun addRemoveExercise(exerciseName: String, view: View, listener: OnExerciseChoiceListener)

    fun addToTraining(trainingViewModel: TrainingViewModel, context: Context, listener: OnExerciseChoiceListener)
    fun getList(exerciseList: List<ExerciseObject>, listener: OnExerciseChoiceListener): List<ExerciseObject>
}