package com.iamagamedev.trainingapp.dataBase

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.repositories.ExerciseRepository

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val exerciseRepository = ExerciseRepository(application)

    private val exerciseList: LiveData<List<ExerciseObject>> = exerciseRepository.getAllExercises()

    fun getAllExercises(): LiveData<List<ExerciseObject>> {
        return exerciseList
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