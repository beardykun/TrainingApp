package com.iamagamedev.trainingapp.ui.exercises

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.view.View
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.utils.Utils
import org.jetbrains.anko.backgroundColor

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

    override fun getList(exerciseList: List<ExerciseObject>,
                         listener: IExerciseChoiceInteractor.OnExerciseChoiceListener): List<ExerciseObject> {
        val oldList = Utils.stringToList(MySharedPreferences.getString(Constants.SAVE_NEW_EXERCISE_LIST))
        val newExList: MutableList<ExerciseObject> = mutableListOf()
        for (i in exerciseList) {
            if (oldList.contains(i.exerciseName)) {
                newExList.add(i)
            }
        }
        return newExList
    }

    override fun addToTraining(trainingViewModel: TrainingViewModel, exerciseChoiceActivity: ExerciseChoiceActivity,
                               listener: IExerciseChoiceInteractor.OnExerciseChoiceListener) {
        val newExList: String = if (MySharedPreferences.isInside(Constants.SAVE_NEW_EXERCISE_LIST)) {
            val sb = StringBuilder()
            if (MySharedPreferences.getString(Constants.SAVE_NEW_EXERCISE_LIST) != "") {
                sb.append(MySharedPreferences.getString(Constants.SAVE_NEW_EXERCISE_LIST))
                sb.append(" , ")
                sb.append(Utils.listToString(list))
            }
            sb.toString()
        } else {
            Utils.listToString(list)
        }

        trainingViewModel.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME)).observe(exerciseChoiceActivity,
                Observer<TrainingObject> { training ->
                    training?.trainingExerciseName = newExList
                    trainingViewModel.updateTraining(training!!)
                })
        listener.onSuccess()
    }
}

