package com.iamagamedev.trainingapp.ui.training

import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject

class TrainingInteractor : ITrainingInteractor {

    override fun deleteTraining(training: TrainingObject, listener: ITrainingInteractor.OnTrainingListener) {
        if (training.trainingName == Constants.DEFAULT_SET) {
            listener.onError("Sorry, can't delete ${Constants.DEFAULT_SET}", 100)
        } else {
            listener.onDeleteSuccess(training)
        }
    }

    override fun startTraining(listener: ITrainingInteractor.OnTrainingListener) {
        if (!MySharedPreferences.isInside(Constants.SAVE_START_TIME)) {
            val startTime = System.currentTimeMillis()
            MySharedPreferences.saveLong(Constants.SAVE_START_TIME, startTime)
        }
        listener.onSuccessStartThisTrainingActivity()
    }
}