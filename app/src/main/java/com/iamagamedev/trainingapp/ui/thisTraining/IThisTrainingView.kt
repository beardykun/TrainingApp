package com.iamagamedev.trainingapp.ui.thisTraining

import com.iamagamedev.trainingapp.ui.general.IGeneralView

interface IThisTrainingView: IGeneralView {
    fun setAdapter(adapter: ThisTrainingAdapter)
}