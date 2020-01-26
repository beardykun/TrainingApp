package com.iamagamedev.trainingapp.ui.thisTraining.fragments.thisTraining


import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.utils.Utils

class ThisTrainingInteractor : IThisTrainingInteractor {

    override fun getAdapter(training: TrainingObject, listener: IThisTrainingInteractor.OnThisTrainingListener) {

        val list = Utils.stringToList(training.trainingExerciseNameList)

        val adapter = ThisTrainingAdapter()
        adapter.swapAdapter(list)
        MySharedPreferences.saveString(
                Utils.getCurrentTrainingList(),
                Utils.listToString(list))
        listener.getAdapterSuccess(adapter)
    }
}