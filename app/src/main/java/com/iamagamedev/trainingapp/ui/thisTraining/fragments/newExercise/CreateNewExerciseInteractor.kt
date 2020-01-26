package com.iamagamedev.trainingapp.ui.thisTraining.fragments.newExercise

import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject

class CreateNewExerciseInteractor()
    : ICreateNewExerciseInteractor {
    override fun addExerciseToDb(isExerciseInside: Boolean, exName: String, exGroupName: String,
                                 exImageId: Int, listener: ICreateNewExerciseInteractor.OnCreateNewExerciseListener) {
        if (isExerciseInside) {
            listener.onError("Already in DB", 11)
            return
        }
        if (exName == "" || exGroupName == "" || exImageId == 0) {
            validateFields(exName, exGroupName, exImageId, listener)
            return
        }
        val exercise = ExerciseObject(null, exName, exGroupName, exImageId.toString())
        listener.onSuccessDBInsert(exercise)
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