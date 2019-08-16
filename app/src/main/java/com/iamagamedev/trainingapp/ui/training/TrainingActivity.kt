package com.iamagamedev.trainingapp.ui.training

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import com.iamagamedev.trainingapp.ui.thisTraining.ThisTrainingActivity
import kotlinx.android.synthetic.main.activity_training.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


class TrainingActivity : GeneralActivityWithAppBar(), ITrainingView,
        TrainingAdapter.OnTrainingItemListener, TrainingAdapter.OnTrainingItemLongListener {

    private var presenter: ITrainingPresenter? = null
    private var trainingViewModel: TrainingViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)
        toolbar_text.text = this::class.java.simpleName
        presenter = TrainingPresenter()

        toolbar_text.text = this::class.java.simpleName

    }

    override fun updateList(trainingName: String) {
        trainingViewModel?.let { it.insertTraining(TrainingObject(null, trainingName)) }
    }

    override fun startActivity() {
        startActivity(ThisTrainingActivity::class.java)
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

    override fun onTrainingListItemClick(name: String) {
        alert("Start Training", "Start training timer?") {
            yesButton {
                MySharedPreferences.saveString(Constants.SAVE_TRAINING_NAME, name)
                presenter?.startTraining()
            }
            noButton { }
        }.show()
    }

    override fun onTrainingListItemLongClick(name: String) {
        alert("Delete", "Want to delete this Training?") {
            yesButton {
                (trainingViewModel?.getTraining(name)?.observe(this@TrainingActivity,
                        Observer { training -> presenter?.deleteTraining(training!!) }))
            }
            noButton { }
        }.show()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        addTrainingBtn.setOnClickListener {
            val fm = supportFragmentManager
            val fragment = CreateTrainingFragment()
            fm.let { fragment.show(fm, "tag") }
        }
        setAdapter()
    }

    override fun onStop() {
        presenter?.onDetachView()
        super.onStop()
    }

    private fun setAdapter() {
        val adapter = TrainingAdapter()
        trainingViewModel?.getTrainings()?.observe(this, Observer<List<TrainingObject>> { trainingList -> adapter.swapAdapter(trainingList!!) })
        adapter.setOnTrainingItemListener(this)
        adapter.setOnTrainingItemLongListener(this)
        trainingRecyclerView.adapter = adapter
    }
}
