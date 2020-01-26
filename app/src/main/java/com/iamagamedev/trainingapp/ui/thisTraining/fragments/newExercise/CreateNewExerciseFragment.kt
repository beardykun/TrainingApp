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

class CreateNewExerciseFragment : Fragment(),
        CreateNewExerciseAdapter.OnCreateNewExerciseListener {

    private var exerciseViewModel: ExerciseViewModel? = null
    private var exImageId = 0
    private var exGroupName = ""
    private var isExerciseInside = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_new_exercise, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        val adapter = CreateNewExerciseAdapter()
        adapter.setOnCreateNewExerciseListener(this)
        createNewExerciseRecyclerView.adapter = adapter
        createNewExOnClick()
    }

    private fun createNewExOnClick() {
        createExerciseBtn.setOnClickListener {
            addExerciseToDb(isExerciseInside(),
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


    override fun chooseMuscleGroup(imageId: Int, muscleGroup: String) {
        exImageId = imageId
        exGroupName = muscleGroup
    }

    private fun addExerciseToDb(isExerciseInside: Boolean, exName: String, exGroupName: String, exImageId: Int) {
        if (isExerciseInside) {
            showError("Already in DB", 11)
            return
        }
        if (exName == "" || exGroupName == "" || exImageId == 0) {
            validateFields(exName, exGroupName, exImageId)
            return
        }
        val exercise = ExerciseObject(null, exName, exGroupName, exImageId.toString())
        exerciseViewModel?.insertExercise(exercise)
    }

    private fun validateFields(exName: String, exGroupName: String, exImageId: Int) {
        when {
            exName == "" -> showError("No name", 22)
            exGroupName == "" -> showError("No group", 33)
            exImageId == 0 -> showError("No image", 44)
        }
    }

    fun showProgress() {
        (activity as ThisTrainingActivity).showProgressView()
    }

    fun hideProgress() {
        (activity as ThisTrainingActivity).hideProgressView()
    }

    private fun showError(error: String, code: Int) {
        (activity as ThisTrainingActivity).showErrorSnack(error)
    }

    fun showError(error: Int, code: Int) {
        (activity as ThisTrainingActivity).showErrorSnack(error.toString())
    }
}
