package com.iamagamedev.trainingapp.ui.general

/**
 * Created by Михан on 07.03.2018.
 */

interface IGeneralInteractorListener {

    fun onError(error: String, code: Int)

    fun onError(error: Int, code: Int)
}
