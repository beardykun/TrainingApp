package com.iamagamedev.trainingapp.ui.thisTraining


import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.utils.Utils

class ThisTrainingInteractor : IThisTrainingInteractor {

    override fun getAdapter(training: TrainingObject, listener: IThisTrainingInteractor.OnThisTrainingListener) {
        try {
            val list = Utils.stringToList(training.trainingExerciseNameList)
            if (list.contains(Constants.EMPTY_STRING)) {
                listener.goToExerciseChoice()
                return
            }
                val adapter = ThisTrainingAdapter()
                adapter.swapAdapter(list)
                MySharedPreferences.saveString(
                        Utils.getCurrentTrainingList(),
                        Utils.listToString(list))
                listener.getAdapterSuccess(adapter)

        } catch (e: IllegalStateException) {
            e.printStackTrace()
            listener.onError("add request for user to add some exercises", 10)
        }
    }
}