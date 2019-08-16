package com.iamagamedev.trainingapp.ui.splash

import android.util.Log
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.app.ThisApplication
import com.iamagamedev.trainingapp.dataBase.ExerciseViewModel
import com.iamagamedev.trainingapp.dataBase.TrainingDatabase
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.dataBase.repositories.ExerciseRepository
import com.iamagamedev.trainingapp.dataBase.repositories.TrainingRepository

class SplashInteractor : ISplashInteractor {

    override fun databaseLoading(listener: ISplashInteractor.OnSplashListener) {
        if (!MySharedPreferences.isInside(SplashActivity::class.java.simpleName)) {
            val exRep = ExerciseRepository(TrainingDatabase.getDatabase(ThisApplication.instance)!!.exerciseDao())
            putInDB(exRep)

            MySharedPreferences.saveString(SplashActivity::class.java.simpleName, SplashActivity::class.java.simpleName)

            val repository = TrainingRepository(TrainingDatabase.getDatabase(ThisApplication.instance)!!.trainingDao())
            repository.insertTrainingAsync(TrainingObject(null, Constants.DEFAULT_SET))
            Log.i("Inserted", "inserted")
        } else
            Log.i("Inserted", "not")
        listener.onSuccess()
    }

    private fun putInDB(repository: ExerciseRepository) {
        repository.insertExerciseAsync(ExerciseObject(null, "Biceps Curls", Constants.BICEPS_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        repository.insertExerciseAsync(ExerciseObject(null, "Shoulder Press", Constants.SHOULDERS_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        repository.insertExerciseAsync(ExerciseObject(null, "Triceps Press", Constants.TRICEPS_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        repository.insertExerciseAsync(ExerciseObject(null, "Pull ups", Constants.BACK_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        repository.insertExerciseAsync(ExerciseObject(null, "Bench Press", Constants.CHEST_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        repository.insertExerciseAsync(ExerciseObject(null, "Squats", Constants.LEGS_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        repository.insertExerciseAsync(ExerciseObject(null, "Curls", Constants.ABS_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
    }
}