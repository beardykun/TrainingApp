package com.iamagamedev.trainingapp.ui.exercises


import android.graphics.Color
import android.view.View
import androidx.lifecycle.Observer
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.app.ThisApplication
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
        val oldList = Utils.stringToList(MySharedPreferences.getString(Utils.getCurrentTrainingList()))
        val newExList: MutableList<ExerciseObject> = mutableListOf()
        for (i in exerciseList) {
            if (!oldList.contains(i.exerciseName)) {
                newExList.add(i)
            }
        }
        return newExList
    }

    override fun addToTraining(trainingViewModel: TrainingViewModel, exerciseChoiceActivity: ExerciseChoiceActivity,
                               listener: IExerciseChoiceInteractor.OnExerciseChoiceListener) {
        //FIXME refactor, trainingViewModel should not be here
        val newExList: String = if (MySharedPreferences.isInside(Utils.getCurrentTrainingList())) {
            val sb = StringBuilder()
            if (list.size == 0 || list.contains(Constants.EMPTY_STRING)) {
                listener.onError(ThisApplication.instance.getString(R.string.exercise_choise_error), 11)
                return
            }
            sb.append(MySharedPreferences.getString(Utils.getCurrentTrainingList()))
            sb.append(" , ")
            sb.append(Utils.listToString(list))
            sb.toString()
        } else {
            Utils.listToString(list)
        }

        trainingViewModel.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
                .observe(exerciseChoiceActivity,
                Observer<TrainingObject> { training ->
                    training?.trainingExerciseNameList = newExList
                    training?.let { trainingViewModel.updateTraining(it) }
                })
        listener.onSuccess()
    }
}

