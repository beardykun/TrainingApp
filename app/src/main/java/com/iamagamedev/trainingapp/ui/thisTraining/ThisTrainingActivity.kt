package com.iamagamedev.trainingapp.ui.thisTraining

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.exercises.ExerciseChoiceActivity
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import com.iamagamedev.trainingapp.utils.Utils
import kotlinx.android.synthetic.main.activity_this_training.*
import kotlinx.android.synthetic.main.fragment_this_training.*
import kotlinx.android.synthetic.main.toolbar.*

class ThisTrainingActivity : GeneralActivityWithAppBar(), IThisTrainingView {


    var trainingViewModel: TrainingViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_this_training)

        toolbar_text.text = this::class.java.simpleName

        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)


        val navController = findNavController(R.id.nav_host_fragment)
// タイトルなどの制御
        setupActionBarWithNavController(
                navController, AppBarConfiguration(
                setOf(
                        R.id.fragment_this_training,
                        R.id.fragment_training_choice
                )
        )
        )
// BottomNavigation の遷移を制御
        nav_view.setupWithNavController(navController)
    }

    override fun showProgress() {
        showProgressView()
    }

    override fun hideProgress() {
        hideProgressView()
    }

    override fun showError(error: String, code: Int) {
        showErrorSnack(error)
    }

    override fun showError(error: Int, code: Int) {
        showErrorSnack(error.toString())
    }

    override fun setAdapter(adapter: ThisTrainingAdapter) {
        recyclerViewThisTraining.adapter = adapter
    }

    override fun goToExerciseChoice() {
        MySharedPreferences.saveString(
                MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME),
                Utils.listToString(mutableListOf(Constants.EMPTY_STRING)))
        startActivity(ExerciseChoiceActivity::class.java)
    }
}
