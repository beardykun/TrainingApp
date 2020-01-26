package com.iamagamedev.trainingapp.ui.thisTraining.fragments.exerciseChoice


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.ExerciseViewModel
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.ThisTrainingActivity
import kotlinx.android.synthetic.main.activity_exercices.*

class ExerciseChoiceFragment : Fragment(), IExerciseChoiceView,
        ExercisesChoiceAdapter.OnExerciseChoiceItemListener {

    private var presenter: IExerciseChoicePresenter? = null
    var exerciseViewModel: ExerciseViewModel? = null
    var trainingViewModel: TrainingViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_exercices, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ExerciseChoicePresenter()

        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)
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
        exerciseViewModel?.getAllExercises()?.observe(viewLifecycleOwner, Observer { exerciseList ->
            adapter.swapAdapter(presenter!!.getList(exerciseList!!))
        })
        adapter.setOnExerciseChoiceItemListener(this)
        exerciseChoiceRecyclerView.adapter = adapter
    }

    override fun addToTraining() {
        trainingViewModel?.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))!!
                .observe(viewLifecycleOwner,
                        Observer<TrainingObject> { training ->
                            training?.trainingExerciseNameList = presenter?.getNewTrainingList()!!
                            training?.let { trainingViewModel?.updateTraining(it) }
                        })
    }
}
