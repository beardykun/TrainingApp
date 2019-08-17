package com.iamagamedev.trainingapp.ui.thisTraining.fragments

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.dataBase.ExerciseViewModel
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.thisTraining.IThisTrainingView
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.thisTraining.ThisTrainingAdapter
import kotlinx.android.synthetic.main.activity_this_training.*
import kotlinx.android.synthetic.main.fragment_this_training.*
import kotlinx.android.synthetic.main.toolbar.*

class ThisTrainingActivity : GeneralActivityWithAppBar(), IThisTrainingView {


    var trainingViewModel: TrainingViewModel? = null
    var exerciseViewModel: ExerciseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_this_training)

        toolbar_text.text = this::class.java.simpleName

        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)
        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)

        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(
                navController, AppBarConfiguration(
                setOf(R.id.fragment_this_training, R.id.fragment_training_choice)))
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
}
