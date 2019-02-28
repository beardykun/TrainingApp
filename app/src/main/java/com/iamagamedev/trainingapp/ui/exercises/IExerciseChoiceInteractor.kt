package com.iamagamedev.trainingapp.ui.exercises

import android.view.View
import com.iamagamedev.trainingapp.ui.general.IGeneralInteractorListener

interface IExerciseChoiceInteractor {

    interface OnExerciseChoiceListener : IGeneralInteractorListener{
        fun onSuccess()

        fun onSuccessAddItem()

        fun getAdapterSuccess(adapter: ExercisesChoiceAdapter)
    }

    fun addRemoveExercise(exerciseName: String, view: View, listener: OnExerciseChoiceListener)

    fun addToTraining(listener: OnExerciseChoiceListener)
}