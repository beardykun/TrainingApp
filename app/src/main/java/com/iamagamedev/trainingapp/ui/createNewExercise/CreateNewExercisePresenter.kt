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

    override fun onError(error: String, code: Int) {
        view?.hideProgress()
    }

    override fun onError(error: Int, code: Int) {
        view?.hideProgress()
    }

    override fun addExerciseToDb() {
        view?.showProgress()
        interactor.addValidateFields(this)
    }

    override fun addExerciseGroupAndImage(position: Int, muscleGroup: String) {
        view?.showProgress()
        interactor.addExerciseGroupAndImage(position, muscleGroup, this)
    }

    override fun addExerciseName(name: String) {
        view?.showProgress()
        interactor.addExerciseName(name, this)
    }
}
