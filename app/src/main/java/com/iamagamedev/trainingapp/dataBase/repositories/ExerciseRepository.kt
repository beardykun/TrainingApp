package com.iamagamedev.trainingapp.dataBase.repositories

import androidx.lifecycle.LiveData
import com.iamagamedev.trainingapp.dataBase.dao.ExerciseDao
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import org.jetbrains.anko.doAsync

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    fun getAllExercises(): LiveData<List<ExerciseObject>> {
        return exerciseDao.getAllExercises()
    }

    fun getExerciseWithName(exName: String):LiveData<ExerciseObject>{
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