package com.iamagamedev.trainingapp.dataBase.objects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.iamagamedev.trainingapp.dataBase.DatabaseContract

@Entity(tableName = DatabaseContract.TABLE_TRAINING)
data class TrainingObject(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_NAME) var trainingName: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_DATE) var trainingDate: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_EXERCISE_NAME) var trainingExerciseName: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_EXERCISE_REPETITIONS) var trainingExerciseRepetitions: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_TIME_BETWEEN_SETS) var trainingTimeBetweenSets: String = "",
        @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_TOTAL_TIME) var trainingTotalTime: String = "")