package com.iamagamedev.trainingapp.ui.thisTraining.fragments.thisTraining

import com.iamagamedev.trainingapp.ui.general.IGeneralView
import java.lang.UnsupportedOperationException

interface IThisTrainingView: IGeneralView {
    fun setAdapter(adapter: ThisTrainingAdapter){
        throw UnsupportedOperationException()
    }
}