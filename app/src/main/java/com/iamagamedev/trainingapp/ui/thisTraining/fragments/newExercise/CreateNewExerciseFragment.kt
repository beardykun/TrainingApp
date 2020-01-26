package com.iamagamedev.trainingapp.ui.thisTraining.fragments.newExercise


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.dataBase.ExerciseViewModel
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.ThisTrainingActivity
import kotlinx.android.synthetic.main.fragment_create_new_exercise.*

class CreateNewExerciseFragment : Fragment(), ICreateNewExerciseView,
        CreateNewExerciseAdapter.OnCreateNewExerciseListener {

    private var presenter: CreateNewExercisePresenter? = null
    private var exerciseViewModel: ExerciseViewModel? = null
    private var exImageId = 0
    private var exGroupName = ""
    private var observer: Observer<ExerciseObject>? = null
    private var isExerciseInside = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_new_exercise, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = CreateNewExercisePresenter()
        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        val adapter = CreateNewExerciseAdapter()
        adapter.setOnCreateNewExerciseListener(this)
        createNewExerciseRecyclerView.adapter = adapter
        createNewExOnClick()
    }

    private fun createNewExOnClick() {
        createExerciseBtn.setOnClickListener {
            presenter?.addExerciseToDb(isExerciseInside(),
                    createExerciseNameEdit.text.toString(), exGroupName, exImageId)
        }
    }

    private fun isExerciseInside(): Boolean {
        exerciseViewModel?.getExerciseWithName(
                createExerciseNameEdit.text.toString())?.observe(viewLifecycleOwner, Observer {
            isExerciseInside = it != null
            Log.i("TAGGER", "inside")
        })
        Log.i("TAGGER", "out")
        return isExerciseInside
    }

    override fun onStop() {
        presenter?.onDetachView()
        super.onStop()
    }

    override fun showProgress() {
        (activity as ThisTrainingActivity).showProgressView()
    }

    override fun hideProgress() {
        (activity as ThisTrainingActivity).hideProgressView()
    }

    override fun showError(error: String, code: Int) {
        (activity as ThisTrainingActivity).showErrorSnack(error)
    }

    override fun showError(error: Int, code: Int) {
        (activity as ThisTrainingActivity).showErrorSnack(error.toString())
    }

    override fun chooseMuscleGroup(imageId: Int, muscleGroup: String) {
        exImageId = imageId
        exGroupName = muscleGroup
    }

    override fun addExToDatabase(exObject: ExerciseObject) {
        exerciseViewModel?.insertExercise(exObject)
    }
}
