package com.iamagamedev.trainingapp.ui.exercises

import com.iamagamedev.trainingapp.ui.general.IGeneralView

interface IExerciseChoiceView : IGeneralView {

    fun startActivityTwo(activity: Class<*>)
}