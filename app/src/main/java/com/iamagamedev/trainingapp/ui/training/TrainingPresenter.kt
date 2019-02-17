package com.iamagamedev.trainingapp.ui.training

class TrainingPresenter(private val interactor: ITrainingInteractor = TrainingInteractor())
    : ITrainingPresenter, ITrainingInteractor.OnTrainingListener {

    private var view: ITrainingView? = null

    override fun onAttachView(view: ITrainingView) {
        this.view = view
    }

    override fun onDetachView() {
        this.view = null
    }

    override fun updateSet(newItem: String) {
        view?.showProgress()
        interactor.updateSet(newItem, this)
    }

    override fun onSuccess() {
        view?.hideProgress()
    }

    override fun onDeleteSuccess() {
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

    override fun deleteTraining(name: String) {
        view?.showProgress()
        interactor.deleteTraining(name, this)
    }

    override fun startTraining() {
        view?.showProgress()
        interactor.startTraining(this)
    }

    override fun onSuccessStartThisTrainingActivity() {
        view?.hideProgress()
        view?.startActivity()
    }

    override fun getAdapter() {
        view?.showProgress()
        interactor.getAdapter(this)
    }

    override fun onSuccessSetAdapter(adapter: TrainingAdapter) {
        view?.hideProgress()
        view?.setAdapter(adapter)
    }
}