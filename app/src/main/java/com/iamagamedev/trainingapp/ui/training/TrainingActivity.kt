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
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.ThisTrainingActivity
import kotlinx.android.synthetic.main.activity_training.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


class TrainingActivity : GeneralActivityWithAppBar(),
        TrainingAdapter.OnTrainingItemListener, TrainingAdapter.OnTrainingItemLongListener {

    private var trainingViewModel: TrainingViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        trainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel::class.java)
        toolbar_text.text = this::class.java.simpleName
    }

    fun updateList(trainingName: String) {
        trainingViewModel?.let { it.insertTraining(TrainingObject(null, trainingName)) }
    }

    private fun deleteTraining(training: TrainingObject) {
        if (training.trainingName == Constants.DEFAULT_SET) {
            showErrorSnack("Sorry, can't delete ${Constants.DEFAULT_SET}")
        }
    }

    private fun startTraining() {
        if (!MySharedPreferences.isInside(Constants.SAVE_START_TIME)) {
            val startTime = System.currentTimeMillis()
            MySharedPreferences.saveLong(Constants.SAVE_START_TIME, startTime)
        }
        startActivity(ThisTrainingActivity::class.java)
    }

    override fun onTrainingListItemClick(name: String) {
        alert("Start Training", "Start training timer?") {
            yesButton {
                MySharedPreferences.saveString(Constants.SAVE_TRAINING_NAME, name)
                startTraining()
            }
            noButton { }
        }.show()
    }

    override fun onTrainingListItemLongClick(name: String) {
        alert("Delete", "Want to delete this Training?") {
            yesButton {
                (trainingViewModel?.getTraining(name)?.observe(this@TrainingActivity,
                        Observer { training -> deleteTraining(training) }))
            }
            noButton { }
        }.show()
    }

    override fun onStart() {
        super.onStart()
        addTrainingBtn.setOnClickListener {
            val fm = supportFragmentManager
            val fragment = CreateTrainingFragment()
            fm.let { fragment.show(fm, "tag") }
        }
        setAdapter()
    }

    private fun setAdapter() {
        val adapter = TrainingAdapter()
        trainingViewModel?.getTrainings()?.observe(this, Observer<List<TrainingObject>> { trainingList -> adapter.swapAdapter(trainingList!!) })
        adapter.setOnTrainingItemListener(this)
        adapter.setOnTrainingItemLongListener(this)
        trainingRecyclerView.adapter = adapter
    }
}
