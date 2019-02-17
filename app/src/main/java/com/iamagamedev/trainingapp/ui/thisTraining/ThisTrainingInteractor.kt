package com.iamagamedev.trainingapp.ui.thisTraining

import android.content.Intent
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.app.ThisApplication
import com.iamagamedev.trainingapp.dataBase.repositories.TrainingRepository
import com.iamagamedev.trainingapp.ui.exercises.ExerciseChoiceActivity
import com.iamagamedev.trainingapp.utils.Utils

class ThisTrainingInteractor : IThisTrainingInteractor {

    override fun addExercise() {
        ThisApplication.instance.startActivity(Intent(ThisApplication.instance,
                ExerciseChoiceActivity::class.java))
    }

    override fun getAdapter(listener: IThisTrainingInteractor.OnThisTrainingListener) {
        try {
            val repository = TrainingRepository(ThisApplication.instance)
            val exercise = repository.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
            val list = Utils.stringToList(exercise.value!!.trainingExerciseName)

            val adapter = ThisTrainingAdapter(list)
            MySharedPreferences.saveString(Constants.SAVE_NEW_EXERCISE_LIST, Utils.listToString(list))
            listener.getAdapterSuccess(adapter)
        }catch (e: IllegalStateException){
            e.printStackTrace()
            //todo add some request for user to add some exercises
            listener.onError("add some request for user to add some exercises", 10)
        }
    }
}