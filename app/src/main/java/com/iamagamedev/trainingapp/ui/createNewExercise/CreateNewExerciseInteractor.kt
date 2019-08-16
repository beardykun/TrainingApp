package com.iamagamedev.trainingapp.ui.createNewExercise

import com.iamagamedev.trainingapp.app.ThisApplication
import com.iamagamedev.trainingapp.dataBase.ExerciseViewModel
import com.iamagamedev.trainingapp.dataBase.TrainingDatabase
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.repositories.ExerciseRepository

class CreateNewExerciseInteractor()
    : ICreateNewExerciseInteractor {
    override fun addExerciseToDb(exObject: ExerciseObject?, exName: String, exGroupName: String,
                                 exImageId: Int, listener: ICreateNewExerciseInteractor.OnCreateNewExerciseListener) {
        if (exName == "" || exGroupName == "" || exImageId == 0) {
            validateFields(exName, exGroupName, exImageId, listener)
            return
        }
        if (exObject != null) {
            listener.onError("Already in DB", 11)
            return
        }
        val exercise = ExerciseObject(null, exName, exGroupName, exImageId.toString())
        addToDB(exercise)
        listener.onSuccessDBInsert()
    }

    private fun addToDB(exObject: ExerciseObject) {
        val exViewModel = ExerciseViewModel(ThisApplication.instance)
        exViewModel.insertExercise(exObject)
        //DBUpdateService.insertNewTraining(ThisApplication.instance, contentValues)
    }

    private fun validateFields(exName: String, exGroupName: String, exImageId: Int, listener:
    ICreateNewExerciseInteractor.OnCreateNewExerciseListener) {
        when {
            exName == "" -> listener.onError("No name", 22)
            exGroupName == "" -> listener.onError("No group", 33)
            exImageId == 0 -> listener.onError("No image", 44)
        }
    }
}