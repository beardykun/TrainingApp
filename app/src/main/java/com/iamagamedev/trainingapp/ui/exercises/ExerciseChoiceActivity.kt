package com.iamagamedev.trainingapp.ui.exercises

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.dataBase.ExerciseViewModel
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import kotlinx.android.synthetic.main.activity_exercices.*

class ExerciseChoiceActivity : IExerciseChoiceView,
        GeneralActivityWithAppBar(), ExercisesChoiceAdapter.OnExerciseChoiceItemListener {

    private var presenter: IExerciseChoicePresenter? = null
    private var viewModel: ExerciseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercices)

        viewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
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

        setAdapter()
    }

    override fun onStop() {
        presenter?.onDetachView()
        super.onStop()
    }

    fun setAdapter() {
        val adapter = ExercisesChoiceAdapter()
        viewModel?.getAllExercises()?.observe(this, Observer<List<ExerciseObject>> { exerciseList ->
            adapter.swapAdapter(exerciseList!!)
        })
        adapter.setOnExerciseChoiceItemListener(this)
        exerciseChoiceRecyclerView.adapter = adapter
    }

    override fun startActivityTwo(activity: Class<*>) {
        startActivity(activity)
    }
}
