package com.iamagamedev.trainingapp.ui.splash

class SplashPresenter(private val interactor: ISplashInteractor = SplashInteractor()) :
        ISplashPresenter, ISplashInteractor.OnSplashListener {

    private var view: ISplashView? = null

    override fun onDetachView() {
        this.view = null
    }

    override fun onAttachView(view: ISplashView) {
        this.view = view
    }

    override fun onError(error: String, code: Int) {
        view?.showError(error, code)
    }

    override fun onError(error: Int, code: Int) {
        view?.showError(error, code)
    }

    override fun onSuccess() {
        view?.hideProgress()
        view?.startApp()
    }

    override fun loadDB() {
        view?.showProgress()
        interactor.databaseLoading(this)
    }
}