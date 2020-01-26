package com.iamagamedev.trainingapp.ui.thisTraining.fragments.exerciseChoice


import android.graphics.Color
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
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.ThisTrainingActivity
import com.iamagamedev.trainingapp.utils.Utils
import kotlinx.android.synthetic.main.activity_exercices.*
import org.jetbrains.anko.backgroundColor

class ExerciseChoiceFragment : Fragment(), ExercisesChoiceAdapter.OnExerciseChoiceItemListener {

    private val list: MutableList<String> = mutableListOf()
    var exerciseViewModel: ExerciseViewModel? = null
    var trainingViewModel: TrainingViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_exercices, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        setAdapter()
    }

    private fun showProgress() {
        (activity as ThisTrainingActivity).showProgressView()
    }

    private fun hideProgress() {
        (activity as ThisTrainingActivity).hideProgressView()
    }

    private fun showError(error: String, code: Int) {
        (activity as ThisTrainingActivity).showErrorSnack(error)
    }

    private fun showError(error: Int, code: Int) {
        (activity as ThisTrainingActivity).showErrorSnack(error.toString())
    }

    override fun onExerciseChoiceItemClick(trainingName: String, view: View) {
        addRemoveExercise(trainingName, view)
    }

    private fun addRemoveExercise(exerciseName: String, view: View) {
        if (list.contains(exerciseName)) {
            list.remove(exerciseName)
            view.backgroundColor = Color.WHITE
        } else {
            list.add(exerciseName)
            view.backgroundColor = Color.BLUE
        }
        addToTraining()
    }

    private fun setAdapter() {
        val adapter = ExercisesChoiceAdapter()
        exerciseViewModel?.getAllExercises()?.observe(viewLifecycleOwner, Observer { exerciseList ->
            adapter.swapAdapter(getList(exerciseList!!))
        })
        adapter.setOnExerciseChoiceItemListener(this)
        exerciseChoiceRecyclerView.adapter = adapter
    }

    private fun addToTraining() {
        trainingViewModel?.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))!!
                .observe(viewLifecycleOwner,
                        Observer<TrainingObject> { training ->
                            training?.trainingExerciseNameList = buildString()
                            training?.let { trainingViewModel?.updateTraining(it) }
                        })
    }

    private fun getList(exerciseList: List<ExerciseObject>): List<ExerciseObject> {
        val oldList = Utils.stringToList(MySharedPreferences.getString(Utils.getCurrentTrainingList()))
        val newExList: MutableList<ExerciseObject> = mutableListOf()
        for (i in exerciseList) {
            if (!oldList.contains(i.exerciseName)) {
                newExList.add(i)
            }
        }
        return newExList
    }

    private fun buildString(): String {
        return if (MySharedPreferences.isInside(Utils.getCurrentTrainingList())) {
            val sb = StringBuilder()
            sb.append(MySharedPreferences.getString(Utils.getCurrentTrainingList()))
            sb.append(" , ")
            sb.append(Utils.listToString(list))
            sb.toString()
        } else {
            Utils.listToString(list)
        }
    }
}
