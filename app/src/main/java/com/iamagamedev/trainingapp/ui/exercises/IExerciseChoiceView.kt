package com.iamagamedev.trainingapp.ui.exercises

import com.iamagamedev.trainingapp.ui.general.IGeneralView

interface IExerciseChoiceView : IGeneralView {

    fun setAdapter(adapter: ExercisesChoiceAdapter)
    fun startActivityTwo(activity: Class<*>)
}