package com.iamagamedev.trainingapp.ui.exercises

import android.graphics.Color
import android.view.View
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.app.ThisApplication
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.repositories.ExerciseRepository
import com.iamagamedev.trainingapp.dataBase.repositories.TrainingRepository
import com.iamagamedev.trainingapp.utils.Utils
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ExerciseChoiceInteractor : IExerciseChoiceInteractor {

    private val list: MutableList<String> = mutableListOf()

    override fun addRemoveExercise(exerciseName: String, view: View,
                                   listener: IExerciseChoiceInteractor.OnExerciseChoiceListener) {
        if (list.contains(exerciseName)) {
            list.remove(exerciseName)
            view.backgroundColor = Color.WHITE
        } else {
            list.add(exerciseName)
            view.backgroundColor = Color.BLUE
        }
        listener.onSuccessAddItem()
    }

    override fun addToTraining(listener: IExerciseChoiceInteractor.OnExerciseChoiceListener) {
        val newExList: String = if (MySharedPreferences.isInside(Constants.SAVE_NEW_EXERCISE_LIST)) {
            val sb = StringBuilder()
            sb.append(MySharedPreferences.getString(Constants.SAVE_NEW_EXERCISE_LIST))
            sb.append(" , ")
            sb.append(Utils.listToString(list))
            sb.toString()
        } else {
            Utils.listToString(list)
        }

        val repository = TrainingRepository(ThisApplication.instance)
        doAsync {
            val training = repository.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
            uiThread {
                training.value?.trainingExerciseName = newExList
                repository.updateTrainingAsync(training.value!!)
                listener.onSuccess()
            }
        }
    }
}