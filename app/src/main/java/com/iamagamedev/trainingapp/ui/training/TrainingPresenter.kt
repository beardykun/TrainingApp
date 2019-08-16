package com.iamagamedev.trainingapp.ui.training

import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject

class TrainingPresenter(private val interactor: ITrainingInteractor = TrainingInteractor())
    : ITrainingPresenter, ITrainingInteractor.OnTrainingListener {

    private var view: ITrainingView? = null

    override fun onAttachView(view: ITrainingView) {
        this.view = view
    }

    override fun onDetachView() {
        this.view = null
    }

    override fun onSuccess() {
        view?.hideProgress()
    }

    override fun onDeleteSuccess(training: TrainingObject) {
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

    override fun deleteTraining(training: TrainingObject) {
        view?.showProgress()
        interactor.deleteTraining(training, this)
    }

    override fun startTraining() {
        view?.showProgress()
        interactor.startTraining(this)
    }

    override fun onSuccessStartThisTrainingActivity() {
        view?.hideProgress()
        view?.startActivity()
    }
}