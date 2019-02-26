package com.iamagamedev.trainingapp.ui.createNewExercise

import com.iamagamedev.trainingapp.app.ThisApplication
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.repositories.ExerciseRepository

class CreateNewExerciseInteractor(
        private val exerciseObject: ExerciseObject = ExerciseObject(null))
    : ICreateNewExerciseInteractor {

    override fun addValidateFields(listener: ICreateNewExerciseInteractor.OnCreateNewExerciseListener) {
        if (exerciseObject.exerciseGroup != "" && exerciseObject.exerciseName != "" && exerciseObject.exerciseImage != "") {
            val exerciseInDB = ExerciseRepository(ThisApplication.instance).getExerciseWithName(exerciseObject.exerciseName)
            if (exerciseInDB == null) {
                listener.onError("Already in DB", 11)
            } else {
                addToDB(exerciseObject)
                listener.onSuccess()
            }
        } else {
            when {
                exerciseObject.exerciseName == "" -> listener.onError("", 22)
                exerciseObject.exerciseGroup == "" -> listener.onError("", 33)
                exerciseObject.exerciseImage == "" -> listener.onError("", 44)
            }
        }
    }

    private fun addToDB(exObject: ExerciseObject) {
        val repository = ExerciseRepository(ThisApplication.instance)
        repository.insertExerciseAsync(exObject)
        //DBUpdateService.insertNewTraining(ThisApplication.instance, contentValues)
    }

    override fun addExerciseGroupAndImage(position: Int, muscleGroup: String, listener: ICreateNewExerciseInteractor.OnCreateNewExerciseListener) {
        exerciseObject.exerciseImage = position.toString()
        exerciseObject.exerciseGroup = muscleGroup
    }

    override fun addExerciseName(name: String, listener: ICreateNewExerciseInteractor.OnCreateNewExerciseListener) {
        exerciseObject.exerciseName = name
    }
}