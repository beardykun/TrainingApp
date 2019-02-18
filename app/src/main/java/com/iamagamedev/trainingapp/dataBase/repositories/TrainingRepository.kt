package com.iamagamedev.trainingapp.dataBase.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import com.iamagamedev.trainingapp.dataBase.TrainingDatabase
import com.iamagamedev.trainingapp.dataBase.dao.TrainingDao
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import org.jetbrains.anko.doAsync

class TrainingRepository(application: Application) {
    private val trainingDao: TrainingDao

    init {
        val db = TrainingDatabase.getDatabase(application)
        trainingDao = db!!.trainingDao()
    }

    fun returnAllTrainings(): LiveData<List<TrainingObject>> {
        return trainingDao.getAllTrainings()
    }

    fun insertTrainingAsync(trainingObject: TrainingObject) {
        doAsync { trainingDao.insertTraining(trainingObject) }
    }

    fun updateTrainingAsync(trainingObject: TrainingObject) {
        doAsync { trainingDao.updateTraining(trainingObject) }
    }

    fun deleteTrainingAsync(trainingObject: TrainingObject) {
        doAsync { trainingDao.deleteTraining(trainingObject) }
    }

    fun getTraining(name: String):LiveData<TrainingObject>{
        return trainingDao.getTraining(name)
    }
}