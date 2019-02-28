package com.iamagamedev.trainingapp.ui.exercises

import android.view.View
import com.iamagamedev.trainingapp.ui.createNewExercise.CreateNewExerciseActivity
import com.iamagamedev.trainingapp.ui.thisTraining.ThisTrainingActivity

class ExerciseChoicePresenter(private val interactor: IExerciseChoiceInteractor = ExerciseChoiceInteractor()) :
        IExerciseChoicePresenter, IExerciseChoiceInteractor.OnExerciseChoiceListener {

    private var view: IExerciseChoiceView? = null

    override fun onAttachView(view: IExerciseChoiceView) {
        this.view = view
    }

    override fun onDetachView() {
        this.view = null
    }

    override fun onSuccess() {
        view?.hideProgress()
        view?.startActivityTwo(ThisTrainingActivity::class.java)
    }

    override fun onError(error: String, code: Int) {
        view?.hideProgress()
    }

    override fun onError(error: Int, code: Int) {
        view?.hideProgress()
    }

    override fun addRemoveExercise(exerciseName: String, view: View) {
        this.view?.showProgress()
        interactor.addRemoveExercise(exerciseName, view, this)
    }

    override fun addToTraining() {
        view?.showProgress()
        interactor.addToTraining(this)
    }

    override fun getAdapterSuccess(adapter: ExercisesChoiceAdapter) {
        view?.hideProgress()
        view?.setAdapter(adapter)
    }

    override fun onSuccessAddItem() {
        view?.hideProgress()
    }

    override fun createExercise() {
        view?.startActivityTwo(CreateNewExerciseActivity::class.java)
    }
}