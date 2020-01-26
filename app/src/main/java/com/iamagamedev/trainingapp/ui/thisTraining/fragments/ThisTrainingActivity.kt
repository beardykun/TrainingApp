package com.iamagamedev.trainingapp.ui.thisTraining.fragments

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.thisTraining.IThisTrainingView
import kotlinx.android.synthetic.main.activity_this_training.*
import kotlinx.android.synthetic.main.toolbar.*

class ThisTrainingActivity : GeneralActivityWithAppBar(), IThisTrainingView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_this_training)

        toolbar_text.text = this::class.java.simpleName

        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(
                navController, AppBarConfiguration(
                setOf(R.id.fragment_this_training, R.id.fragment_training_choice, R.id.fragment_create_exercise)))
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
