package com.iamagamedev.trainingapp.dataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.repositories.ExerciseRepository

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val exerciseRepository:ExerciseRepository = ExerciseRepository(TrainingDatabase.getDatabase(application)!!.exerciseDao())

    fun getAllExercises(): LiveData<List<ExerciseObject>> {
        return exerciseRepository.getAllExercises()
    }

    fun getExerciseWithName(name: String): LiveData<ExerciseObject> {
        return exerciseRepository.getExerciseWithName(name)
    }

    fun insertExercise(exerciseObject: ExerciseObject) {
        exerciseRepository.insertExerciseAsync(exerciseObject)
    }

    fun deleteExercise(exerciseObject: ExerciseObject) {
        exerciseRepository.deleteExerciseAsync(exerciseObject)
    }

    fun updateExercise(exerciseObject: ExerciseObject) {
        exerciseRepository.updateExerciseAsync(exerciseObject)
    }
}