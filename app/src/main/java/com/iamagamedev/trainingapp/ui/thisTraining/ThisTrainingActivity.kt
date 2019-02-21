package com.iamagamedev.trainingapp.ui.thisTraining

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import kotlinx.android.synthetic.main.activity_this_training.*

class ThisTrainingActivity : GeneralActivityWithAppBar(), IThisTrainingView {

    private var presenter: IThisTrainingPresenter? = null
    private var trainingViewModel: TrainingViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_this_training)

        presenter = ThisTrainingPresenter()
        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        trainingViewModel?.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
                ?.observe(this, Observer<TrainingObject> { training ->
                    presenter?.getAdapter(training!!)
                })
        fabThisTraining.setOnClickListener { presenter?.addExercise() }
    }

    override fun onStop() {
        presenter?.onDetachView()
        super.onStop()
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
}
