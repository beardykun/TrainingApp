package com.iamagamedev.trainingapp.ui.thisTraining.fragments.thisTraining


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
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.ThisTrainingActivity
import kotlinx.android.synthetic.main.fragment_this_training.*

class ThisTrainingFragment : Fragment(), IThisTrainingView {

    private var presenter: IThisTrainingPresenter? = null
    var trainingViewModel: TrainingViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_this_training, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ThisTrainingPresenter()
        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        trainingViewModel?.getTraining(MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME))
                ?.observe(viewLifecycleOwner, Observer<TrainingObject> { training ->
                    presenter?.getAdapter(training!!)
                })
    }

    override fun onStop() {
        presenter?.onDetachView()
        trainingViewModel = null
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
