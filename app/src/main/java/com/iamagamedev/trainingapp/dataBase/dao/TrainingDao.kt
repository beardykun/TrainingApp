package com.iamagamedev.trainingapp.dataBase.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject

@Dao
interface TrainingDao {

    @Query("SELECT * FROM table_training")
    fun getAllTrainings(): LiveData<List<TrainingObject>>

    @Query("SELECT * FROM table_training WHERE trainingName LIKE :name LIMIT 1")
    fun getTraining(name: String): LiveData<TrainingObject>

    @Insert
    fun insertTraining(trainingObject: TrainingObject)

    @Update
    fun updateTraining(trainingObject: TrainingObject)

    @Delete
    fun deleteTraining(trainingObject: TrainingObject)

}