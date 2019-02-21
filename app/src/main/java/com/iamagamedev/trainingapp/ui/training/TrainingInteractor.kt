package com.iamagamedev.trainingapp.ui.training

import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.app.ThisApplication
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.dataBase.repositories.TrainingRepository

class TrainingInteractor : ITrainingInteractor {

    override fun deleteTraining(name: String, listener: ITrainingInteractor.OnTrainingListener) {
        if (name == Constants.DEFAULT_SET) {
            listener.onError("Sorry, can't delete ${Constants.DEFAULT_SET}", 100)
        } else {
            val repository = TrainingRepository(ThisApplication.instance)
            val training = repository.getTraining(name)
            repository.deleteTrainingAsync(training.value!!)
            listener.onDeleteSuccess()
        }
    }


    override fun updateSet(viewModel: TrainingViewModel, newItem: String, listener: ITrainingInteractor.OnTrainingListener) {
        val newTraining = TrainingObject(null, newItem)
        viewModel.insertTraining(newTraining)
        listener.onSuccess()
    }

    override fun startTraining(listener: ITrainingInteractor.OnTrainingListener) {
        if (!MySharedPreferences.isInside(Constants.SAVE_START_TIME)) {
            val startTime = System.currentTimeMillis()
            MySharedPreferences.saveLong(Constants.SAVE_START_TIME, startTime)
        }
        listener.onSuccessStartThisTrainingActivity()
    }
}