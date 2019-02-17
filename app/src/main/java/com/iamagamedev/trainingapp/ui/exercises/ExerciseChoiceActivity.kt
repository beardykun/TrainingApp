package com.iamagamedev.trainingapp.ui.exercises

import android.os.Bundle
import android.view.View
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import kotlinx.android.synthetic.main.activity_exercices.*

class ExerciseChoiceActivity: IExerciseChoiceView,
        GeneralActivityWithAppBar(), ExercisesChoiceAdapter.OnExerciseChoiceItemListener {

    private var presenter: IExerciseChoicePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercices)

        presenter = ExerciseChoicePresenter()
    }

    override fun onExerciseChoiceItemClick(trainingName: String, view: View) {
        presenter?.addRemoveExercise(trainingName, view)
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

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        btnAddExercise.setOnClickListener { presenter?.addToTraining() }
        addNewExerciseFAB.setOnClickListener { presenter?.createExercise() }

        presenter?.getList()
    }

    override fun onStop() {
        presenter?.onDetachView()
        super.onStop()
    }

    override fun setAdapter(adapter: ExercisesChoiceAdapter) {
        adapter.setOnExerciseChoiceItemListener(this)
        exerciseChoiceRecyclerView.adapter = adapter
    }

    override fun startActivityTwo(activity: Class<*>) {
        startActivity(activity)
    }
}
