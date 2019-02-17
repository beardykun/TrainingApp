package com.iamagamedev.trainingapp.ui.createNewExercise

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import kotlinx.android.synthetic.main.activity_create_new_exercise.*

class CreateNewExerciseActivity : GeneralActivityWithAppBar(), ICreateNewExerciseView,
CreateNewExerciseAdapter.OnCreateNewExerciseListener, TextView.OnEditorActionListener{

    private var presenter: CreateNewExercisePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_exercise)

        presenter = CreateNewExercisePresenter()
        createExerciseNameEdit.setOnEditorActionListener(this)
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        val adapter = CreateNewExerciseAdapter()
        adapter.setOnCreateNewExerciseListener(this)
        createNewExerciseRecyclerView.adapter = adapter
        createExerciseBtn.setOnClickListener { presenter?.addExerciseToDb() }
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

    override fun chooseMuscleGroup(position: Int, muscleGroup: String) {
        presenter?.addExerciseGroupAndImage(position, muscleGroup)
    }

    override fun onEditorAction(textView: TextView, i: Int, keyEvent: KeyEvent): Boolean {
        if (i == EditorInfo.IME_ACTION_DONE) {
            presenter?.addExerciseName(textView.text.toString())
            return true
        }
        return false
    }
}
