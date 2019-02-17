package com.iamagamedev.trainingapp.dataBase.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import com.iamagamedev.trainingapp.dataBase.TrainingDatabace
import com.iamagamedev.trainingapp.dataBase.dao.ExerciseDao
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import org.jetbrains.anko.doAsync

class ExerciseRepository(application: Application) {

    private val exerciseDao: ExerciseDao

    init {
        val db = TrainingDatabace.getDatabase(application)
        exerciseDao = db!!.exerciseDao()
    }

    fun getAllExercises():LiveData<List<ExerciseObject>>{
        return exerciseDao.getAllExercises()
    }

    fun detExersiseWithName(exName: String):LiveData<ExerciseObject>{
        return exerciseDao.getExerciseWithName(exName)
    }

    fun insertExerciseAsync(exerciseObject: ExerciseObject){
        doAsync { exerciseDao.insertExercise(exerciseObject) }
    }

    fun updateExerciseAsync(exerciseObject: ExerciseObject){
        doAsync { exerciseDao.updateExercise(exerciseObject) }
    }

    fun deleteExerciseAsync(exerciseObject: ExerciseObject){
        doAsync { exerciseDao.deleteExercise(exerciseObject) }
    }
}