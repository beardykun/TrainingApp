package com.iamagamedev.trainingapp.ui.training

import android.os.Bundle
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import com.iamagamedev.trainingapp.ui.general.GeneralActivityWithAppBar
import com.iamagamedev.trainingapp.ui.thisTraining.ThisTrainingActivity
import kotlinx.android.synthetic.main.activity_training.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class TrainingActivity : GeneralActivityWithAppBar(), ITrainingView,
        TrainingAdapter.OnTrainingItemListener, TrainingAdapter.OnTrainingItemLongListener{

    private var presenter: ITrainingPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        presenter = TrainingPresenter()
    }

    override fun onTrainingListItemClick(name: String) {
        MySharedPreferences.saveString(Constants.SAVE_TRAINING_NAME, name)
        alert ("Start Training", "Start training timer?"){
            yesButton { presenter?.startTraining() }
            noButton {  }
        }.show()
    }

    override fun onTrainingListItemLongClick(name: String) {
        alert("Delete", "Want to delete this Training?"){
            yesButton { presenter?.deleteTraining(name) }
            noButton {  }
        }.show()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        addTrainingBtn.setOnClickListener {
            val fm = supportFragmentManager
            val fragment = CreateTrainingFragment()
            fragment.show(fm, "tag")
        }
        presenter?.getAdapter()
    }

    override fun onStop() {
        presenter?.onDetachView()
        super.onStop()
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

    override fun setAdapter(adapter: TrainingAdapter) {
        adapter.setOnTrainingItemListener(this)
        adapter.setOnTrainingItemLongListener(this)
        trainingRecyclerView.adapter = adapter
    }

    override fun updateList(trainingName: String) {
        presenter?.updateSet(trainingName)
    }

    override fun startActivity() {
        startActivity(ThisTrainingActivity::class.java)
    }
}
