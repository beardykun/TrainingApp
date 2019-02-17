package com.iamagamedev.trainingapp.ui.thisTraining

import android.os.Bundle
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import com.iamagamedev.trainingapp.utils.Utils
import kotlinx.android.synthetic.main.activity_this_training.*
import org.jetbrains.anko.toast

class ThisTrainingActivity : GeneralActivityWithAppBar(), IThisTrainingView {

    private var presenter: IThisTrainingPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_this_training)

        presenter = ThisTrainingPresenter()
        toast(Utils.getCurrentTime())
        presenter?.getAdapter()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        fabThisTraining.setOnClickListener { presenter?.addExercise() }
    }

    override fun onDestroy() {
        presenter?.onDetachView()
        super.onDestroy()
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
