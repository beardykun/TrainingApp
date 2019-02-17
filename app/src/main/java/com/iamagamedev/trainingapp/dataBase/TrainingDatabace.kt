package com.iamagamedev.trainingapp.dataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.iamagamedev.trainingapp.dataBase.dao.ExerciseDao
import com.iamagamedev.trainingapp.dataBase.dao.TrainingDao
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject

@Database(entities = [ExerciseObject::class, TrainingObject::class], version = 1)
abstract class TrainingDatabace : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun trainingDao(): TrainingDao

    companion object {
        @Volatile
        private var INSTANCE: TrainingDatabace? = null

        fun getDatabase(context: Context): TrainingDatabace? {
            if (INSTANCE == null) {
                synchronized(TrainingDatabace::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                TrainingDatabace::class.java,
                                DatabaseContract.DATABASE_NAME).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}