package com.iamagamedev.trainingapp.ui.createNewExercise

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

    override fun onSuccessDBInsert() {
        view?.hideProgress()
        view?.onSuccessDBInsert()
    }

    override fun onError(error: String, code: Int) {
        view?.hideProgress()
        view?.showError(error, code)
    }

    override fun onError(error: Int, code: Int) {
        view?.hideProgress()
        view?.showError(error, code)
    }

    override fun addExerciseToDb(exerciseObject: ExerciseObject?, exName: String, exGroupName: String, exImageId: Int) {
        view?.showProgress()
        interactor.addExerciseToDb(exerciseObject, exName, exGroupName, exImageId, this)
    }
}
