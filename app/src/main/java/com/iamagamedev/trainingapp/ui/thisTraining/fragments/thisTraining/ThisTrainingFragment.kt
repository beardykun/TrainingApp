package com.iamagamedev.trainingapp.ui.thisTraining.fragments.thisTraining


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
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.ThisTrainingActivity
import kotlinx.android.synthetic.main.fragment_this_training.*

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
    }

    override fun onStop() {
        presenter?.onDetachView()
        super.onStop()
    }

    override fun setAdapter(adapter: ThisTrainingAdapter) {
        recyclerViewThisTraining.adapter = adapter
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
