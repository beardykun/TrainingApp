package com.iamagamedev.trainingapp.ui.splash

import android.os.Bundle
import com.iamagamedev.trainingapp.R
import com.iamagamedev.trainingapp.ui.general.GeneralActivity
import com.iamagamedev.trainingapp.ui.main.MainActivity

class SplashActivity : GeneralActivity(), ISplashView {

    private var presenter: SplashPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)

        presenter = SplashPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter?.onAttachView(this)
        presenter?.loadDB()
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

    override fun startApp() {
        startActivity(MainActivity::class.java, true)
    }
}
