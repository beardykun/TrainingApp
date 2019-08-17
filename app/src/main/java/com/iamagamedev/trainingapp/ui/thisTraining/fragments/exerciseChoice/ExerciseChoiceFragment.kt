package com.iamagamedev.trainingapp.ui.thisTraining.fragments.exerciseChoice


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.ThisTrainingActivity
import kotlinx.android.synthetic.main.activity_exercices.*

class ExerciseChoiceFragment : Fragment(), IExerciseChoiceView,
        ExercisesChoiceAdapter.OnExerciseChoiceItemListener {

    private var presenter: IExerciseChoicePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_exercices, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ExerciseChoicePresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        setAdapter()
    }

    override fun onStop() {
        presenter?.onDetachView()
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        presenter?.addToTraining((activity as ThisTrainingActivity).trainingViewModel!!, activity as ThisTrainingActivity)
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

    override fun onExerciseChoiceItemClick(trainingName: String, view: View) {
        presenter?.addRemoveExercise(trainingName, view)
    }

    private fun setAdapter() {
        val adapter = ExercisesChoiceAdapter()
        (activity as ThisTrainingActivity).exerciseViewModel?.getAllExercises()?.observe(this, Observer { exerciseList ->
            adapter.swapAdapter(presenter!!.getList(exerciseList!!))
        })
        adapter.setOnExerciseChoiceItemListener(this)
        exerciseChoiceRecyclerView.adapter = adapter
    }
}
