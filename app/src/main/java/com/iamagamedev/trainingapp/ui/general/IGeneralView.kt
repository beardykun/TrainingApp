package com.iamagamedev.trainingapp.ui.general

/**
 * Created by Михан on 07.03.2018.
 */

interface IGeneralView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String, code: Int)

    fun showError(error: Int, code: Int)

}
