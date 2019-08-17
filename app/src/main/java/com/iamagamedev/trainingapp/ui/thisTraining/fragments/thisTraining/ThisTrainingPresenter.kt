package com.iamagamedev.trainingapp.ui.thisTraining.fragments.thisTraining

import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject

class ThisTrainingPresenter(private val interactor: IThisTrainingInteractor = ThisTrainingInteractor())
    : IThisTrainingPresenter, IThisTrainingInteractor.OnThisTrainingListener {

    private var view: IThisTrainingView? = null

    override fun onAttachView(view: IThisTrainingView) {
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

    override fun getAdapter(training: TrainingObject) {
        view?.showProgress()
        interactor.getAdapter(training, this)
    }

    override fun getAdapterSuccess(adapter: ThisTrainingAdapter) {
        view?.hideProgress()
        view?.setAdapter(adapter)
    }
}