package com.iamagamedev.trainingapp.ui.thisTraining.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.exercises.ExerciseChoiceActivity
import com.iamagamedev.trainingapp.ui.thisTraining.*
import com.iamagamedev.trainingapp.ui.training.*
import com.iamagamedev.trainingapp.utils.Utils
import kotlinx.android.synthetic.main.fragment_this_training.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class ThisTrainingFragment : Fragment(), IThisTrainingView {

    private var presenter: IThisTrainingPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_this_training, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ThisTrainingPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        (activity as ThisTrainingActivity).trainingViewModel?.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
                ?.observe(this, Observer<TrainingObject> { training ->
                    presenter?.getAdapter(training!!)
                })
        fabThisTraining.setOnClickListener { startActivity(Intent(activity, ExerciseChoiceActivity::class.java)) }
    }

    override fun onStop() {
        presenter?.onDetachView()
        super.onStop()
    }

    override fun setAdapter(adapter: ThisTrainingAdapter) {
        recyclerViewThisTraining.adapter = adapter
    }

    override fun goToExerciseChoice() {
        MySharedPreferences.saveString(
                MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME),
                Utils.listToString(mutableListOf(Constants.EMPTY_STRING)))
        startActivity(Intent(activity, ExerciseChoiceActivity::class.java))
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
}
