package com.iamagamedev.trainingapp.ui.thisTraining.fragments.exerciseChoice

import android.content.Context
import android.view.View
import com.iamagamedev.trainingapp.dataBase.TrainingViewModel
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.ui.createNewExercise.CreateNewExerciseActivity
import com.iamagamedev.trainingapp.ui.thisTraining.fragments.ThisTrainingActivity

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
    }

    override fun onError(error: String, code: Int) {
        view?.hideProgress()
        view?.showError(error, code)
    }

    override fun onError(error: Int, code: Int) {
        view?.hideProgress()
        view?.showError(error, code)
    }

    override fun addRemoveExercise(exerciseName: String, view: View) {
        this.view?.showProgress()
        interactor.addRemoveExercise(exerciseName, view, this)
    }

    override fun addToTraining(trainingViewModel: TrainingViewModel, context: Context) {
        view?.showProgress()
        interactor.addToTraining(trainingViewModel, context, this)
    }

    override fun getList(exerciseList: List<ExerciseObject>): List<ExerciseObject> {
        return interactor.getList(exerciseList, this)
    }

    override fun onSuccessAddItem() {
        view?.hideProgress()
    }
}