package com.iamagamedev.trainingapp.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iamagamedev.trainingapp.dataBase.dao.ExerciseDao
import com.iamagamedev.trainingapp.dataBase.dao.TrainingDao
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject
import com.iamagamedev.trainingapp.dataBase.objects.TrainingObject

@Database(entities = [ExerciseObject::class, TrainingObject::class], version = 1)
abstract class TrainingDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun trainingDao(): TrainingDao

    companion object {
        @Volatile
        private var INSTANCE: TrainingDatabase? = null

        fun getDatabase(context: Context): TrainingDatabase? {
            if (INSTANCE == null) {
                synchronized(TrainingDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                TrainingDatabase::class.java,
                                DatabaseContract.DATABASE_NAME).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}