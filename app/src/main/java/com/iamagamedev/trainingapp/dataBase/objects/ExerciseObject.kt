package com.iamagamedev.trainingapp.dataBase.objects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.iamagamedev.trainingapp.dataBase.DatabaseContract

@Entity(tableName = DatabaseContract.TABLE_EXERCISE)
data class ExerciseObject(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = DatabaseContract.ExerciseColumns.EXERCISE_NAME) var exerciseName: String = "",
        @ColumnInfo(name = DatabaseContract.ExerciseColumns.EXERCISE_GROUP) var exerciseGroup: String = "",
        @ColumnInfo(name = DatabaseContract.ExerciseColumns.EXERCISE_IMAGE) var exerciseImage: String = ""
)