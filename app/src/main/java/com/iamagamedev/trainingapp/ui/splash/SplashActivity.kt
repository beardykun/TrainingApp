package com.iamagamedev.trainingapp.ui.splash

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.ExerciseViewModel
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.general.GeneralActivity
import com.iamagamedev.trainingapp.ui.main.MainActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : GeneralActivity() {

    private var trainingViewModel: TrainingViewModel? = null
    private var exerciseViewModel: ExerciseViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)

        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)
        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
        databaseLoading()
    }

    private fun startApp() {
        hideProgressView()
        Timer().schedule(3000) {
            startActivity(MainActivity::class.java, true)
        }
    }

    private fun databaseLoading() {
        showProgressView()
        if (!MySharedPreferences.isInside(SplashActivity::class.java.simpleName)) {
            putInDB()

            MySharedPreferences.saveString(SplashActivity::class.java.simpleName, SplashActivity::class.java.simpleName)

            trainingViewModel?.insertTraining(TrainingObject(null, Constants.DEFAULT_SET))
            Log.i("Inserted", "inserted")
        } else {
            Log.i("Inserted", "not")
        }
        startApp()
    }

    private fun putInDB() {
        exerciseViewModel?.insertExercise(ExerciseObject(null, "Biceps Curls", Constants.BICEPS_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        exerciseViewModel?.insertExercise(ExerciseObject(null, "Shoulder Press", Constants.SHOULDERS_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        exerciseViewModel?.insertExercise(ExerciseObject(null, "Triceps Press", Constants.TRICEPS_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        exerciseViewModel?.insertExercise(ExerciseObject(null, "Pull ups", Constants.BACK_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        exerciseViewModel?.insertExercise(ExerciseObject(null, "Bench Press", Constants.CHEST_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        exerciseViewModel?.insertExercise(ExerciseObject(null, "Squats", Constants.LEGS_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
        exerciseViewModel?.insertExercise(ExerciseObject(null, "Curls", Constants.ABS_GROUP, R.drawable.ic_fitness_center_black_24dp.toString()))
    }
}
