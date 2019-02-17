package com.iamagamedev.trainingapp.ui.training

import com.iamagamedev.trainingapp.ui.general.IGeneralView

interface ITrainingView : IGeneralView {

    fun updateList(trainingName: String)

    fun startActivity()
    fun setAdapter(adapter: TrainingAdapter)
}