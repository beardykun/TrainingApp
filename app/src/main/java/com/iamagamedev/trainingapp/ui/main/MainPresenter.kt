package com.iamagamedev.trainingapp.ui.main

class MainPresenter(private val interactor: IMainInteractor = MainInteractor()): IMainPresenter,
        IMainInteractor.OnMainListener {

    private var view: IMainView? = null

    override fun onAttachView(view: IMainView) {
        this.view = view
    }

    override fun onDetachView() {
        this.view = null
    }

    override fun onSuccess() {
        view?.hideProgress()
    }

    override fun onError(error: Int, code: Int) {
        view?.hideProgress()
    }

    override fun onError(error: String, code: Int) {
        view?.hideProgress()
    }

    override fun getTrainingsProductivity() {
        //view?.showProgress()
        interactor.getTrainingsProductivity()
    }
}