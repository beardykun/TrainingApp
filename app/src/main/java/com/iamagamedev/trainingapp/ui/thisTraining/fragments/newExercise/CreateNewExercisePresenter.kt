package com.iamagamedev.trainingapp.ui.thisTraining.fragments.newExercise

import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject

class CreateNewExercisePresenter(private val interactor: ICreateNewExerciseInteractor = CreateNewExerciseInteractor()) :
        ICreateNewExercisePresenter, ICreateNewExerciseInteractor.OnCreateNewExerciseListener {

    private var view: ICreateNewExerciseView? = null

    override fun onAttachView(view: ICreateNewExerciseView) {
        this.view = view
    }

    override fun onDetachView() {
        view = null
    }

    override fun onSuccess() {
        view?.hideProgress()
    }

    override fun onSuccessDBInsert(exObject: ExerciseObject) {
        view?.hideProgress()
        view?.addExToDatabase(exObject)
    }

    override fun onError(error: String, code: Int) {
        view?.hideProgress()
        view?.showError(error, code)
    }

    override fun onError(error: Int, code: Int) {
        view?.hideProgress()
        view?.showError(error, code)
    }

    override fun addExerciseToDb(isExerciseInside: Boolean, exName: String, exGroupName: String, exImageId: Int) {
        view?.showProgress()
        interactor.addExerciseToDb(isExerciseInside, exName, exGroupName, exImageId, this)
    }
}
