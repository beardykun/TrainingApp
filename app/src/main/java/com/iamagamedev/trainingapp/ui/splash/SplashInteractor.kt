package com.iamagamedev.trainingapp.ui.splash

import android.util.Log
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.app.ThisApplication
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.dataBase.repositories.ExerciseRepository
import com.iamagamedev.trainingapp.dataBase.repositories.TrainingRepository

class SplashInteractor : ISplashInteractor {

    override fun databaseLoading(listener: ISplashInteractor.OnSplashListener) {
        if (!MySharedPreferences.isInside(SplashActivity::class.java.simpleName)) {
            putInDB("Biceps Curls", R.drawable.ic_fitness_center_black_24dp, Constants.BICEPS_GROUP)
            putInDB("Triceps Press", R.drawable.ic_fitness_center_black_24dp, Constants.TRICEPS_GROUP)
            putInDB("Shoulder Press", R.drawable.ic_fitness_center_black_24dp, Constants.SHOULDERS_GROUP)
            putInDB("Pull ups", R.drawable.ic_fitness_center_black_24dp, Constants.BACK_GROUP)
            putInDB("Bench Press", R.drawable.ic_fitness_center_black_24dp, Constants.CHEST_GROUP)
            putInDB("Squats", R.drawable.ic_fitness_center_black_24dp, Constants.LEGS_GROUP)
            putInDB("Curls", R.drawable.ic_fitness_center_black_24dp, Constants.ABS_GROUP)
            MySharedPreferences.saveString(SplashActivity::class.java.simpleName, SplashActivity::class.java.simpleName)

            val repository = TrainingRepository(ThisApplication.instance)
            repository.insertTrainingAsync(TrainingObject(null, Constants.DEFAULT_SET))
            Log.i("Inserted", "inserted")
        }else
            Log.i("Inserted", "not")
        listener.onSuccess()
    }

    private fun putInDB(exerciseName: String, exerciseImage: Int, exerciseGroup: String) {
        val repository = ExerciseRepository(ThisApplication.instance)
        repository.insertExerciseAsync(ExerciseObject(null, exerciseName, exerciseGroup, exerciseImage.toString()))
    }
}