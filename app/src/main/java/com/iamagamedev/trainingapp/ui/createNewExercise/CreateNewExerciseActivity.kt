package com.iamagamedev.trainingapp.ui.createNewExercise

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.dataBase.ExerciseViewModel
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.exercises.ExerciseChoiceActivity
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import kotlinx.android.synthetic.main.activity_create_new_exercise.*
import kotlinx.android.synthetic.main.toolbar.*

class CreateNewExerciseActivity : GeneralActivityWithAppBar(), ICreateNewExerciseView,
        CreateNewExerciseAdapter.OnCreateNewExerciseListener {

    private var presenter: CreateNewExercisePresenter? = null
    private var exerciseViewModel: ExerciseViewModel? = null
    private var exImageId = 0
    private var exGroupName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_exercise)

        toolbar_text.text = this::class.java.simpleName

        presenter = CreateNewExercisePresenter()
        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        val adapter = CreateNewExerciseAdapter()
        adapter.setOnCreateNewExerciseListener(this)
        createNewExerciseRecyclerView.adapter = adapter
        createExerciseBtn.setOnClickListener {
            exerciseViewModel?.getExerciseWithName(createExerciseNameEdit.text.toString())?.observe(
                    this, Observer<ExerciseObject> { exerciseObj ->
                presenter?.addExerciseToDb(exerciseObj,
                        createExerciseNameEdit.text.toString(), exGroupName, exImageId)
            })
        }
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

    override fun chooseMuscleGroup(imageId: Int, muscleGroup: String) {
        exImageId = imageId
        exGroupName = muscleGroup
    }

    override fun onSuccessDBInsert() {
        startActivity(ExerciseChoiceActivity::class.java)
    }
}
