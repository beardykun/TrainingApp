package com.iamagamedev.trainingapp.dataBase

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.dataBase.repositories.TrainingRepository

class TrainingViewModel(application: Application) : AndroidViewModel(application) {

    private val trainingRepository: TrainingRepository = TrainingRepository(application)

    private val trainingList: LiveData<List<TrainingObject>> = trainingRepository.returnAllTrainings()

    fun getTrainings(): LiveData<List<TrainingObject>> {
        return trainingList
    }

    fun getTraining(trainingName: String): LiveData<TrainingObject> {
        return trainingRepository.getTraining(trainingName)
    }

    fun insertTraining(trainingObject: TrainingObject){
        trainingRepository.insertTrainingAsync(trainingObject)
    }

    fun updateTraining(trainingObject: TrainingObject){
        trainingRepository.updateTrainingAsync(trainingObject)
    }

    fun deleteTraining(trainingName: String){
        trainingRepository.getTraining(trainingName)
    }
}