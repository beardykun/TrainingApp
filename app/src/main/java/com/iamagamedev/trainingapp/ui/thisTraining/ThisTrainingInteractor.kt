package com.iamagamedev.trainingapp.ui.thisTraining

import android.content.Intent
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.app.ThisApplication
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.exercises.ExerciseChoiceActivity
import com.iamagamedev.trainingapp.utils.Utils

class ThisTrainingInteractor : IThisTrainingInteractor {

    override fun addExercise() {
        ThisApplication.instance.startActivity(Intent(ThisApplication.instance,
                ExerciseChoiceActivity::class.java))
    }

    override fun getAdapter(training: TrainingObject, listener: IThisTrainingInteractor.OnThisTrainingListener) {
        try {
            val list = Utils.stringToList(training.trainingExerciseName)
            val adapter = ThisTrainingAdapter(list)
            MySharedPreferences.saveString(Constants.SAVE_NEW_EXERCISE_LIST, Utils.listToString(list))
            listener.getAdapterSuccess(adapter)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            listener.onError("add some request for user to add some exercises", 10)
        }
    }
}