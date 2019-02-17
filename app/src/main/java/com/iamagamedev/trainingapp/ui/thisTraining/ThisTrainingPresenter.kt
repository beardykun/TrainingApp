package com.iamagamedev.trainingapp.ui.thisTraining

class ThisTrainingPresenter(private val interactor: IThisTrainingInteractor = ThisTrainingInteractor())
    : IThisTrainingPresenter, IThisTrainingInteractor.OnThisTrainingListener {

    private var view: IThisTrainingView? = null

    override fun onAttachView(view: IThisTrainingView) {
        this.view = view
    }

    override fun onDetachView() {
        this.view = null
    }

    override fun addExercise() {
        interactor.addExercise()
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

    override fun getAdapter() {
        view?.showProgress()
        interactor.getAdapter(this)
    }

    override fun getAdapterSuccess(adapter: ThisTrainingAdapter) {
        view?.hideProgress()
        view?.setAdapter(adapter)
    }
}