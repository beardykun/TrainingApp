package com.iamagamedev.trainingapp.ui.thisTraining.fragments.exerciseChoice


import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.ThisTrainingActivity
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
        val oldList = Utils.stringToList(MySharedPreferences.getString(Utils.getCurrentTrainingList()))
        val newExList: MutableList<ExerciseObject> = mutableListOf()
        for (i in exerciseList) {
            if (!oldList.contains(i.exerciseName)) {
                newExList.add(i)
            }
        }
        return newExList
    }

    override fun addToTraining(listener: IExerciseChoiceInteractor.OnExerciseChoiceListener): String {
        listener.onSuccess()
        return buildString()
    }

    private fun buildString(): String {
        return if (MySharedPreferences.isInside(Utils.getCurrentTrainingList())) {
            val sb = StringBuilder()
            sb.append(MySharedPreferences.getString(Utils.getCurrentTrainingList()))
            sb.append(" , ")
            sb.append(Utils.listToString(list))
            sb.toString()
        } else {
            Utils.listToString(list)
        }
    }
}

