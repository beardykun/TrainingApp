package com.iamagamedev.trainingapp.dataBase

class DatabaseContract {

    companion object {
        const val DATABASE_NAME = "TRAINING_DATABASE"
        const val TABLE_TRAINING = "TABLE_TRAINING"
        const val TABLE_EXERCISE = "TABLE_EXERCISE"
    }

    class TrainingColumns {
        companion object {
            const val TRAINING_NAME = "trainingName"
            const val TRAINING_DATE = "trainingDate"
            const val TRAINING_EXERCISE_NAME = "trainingExerciseNameList"
            const val TRAINING_EXERCISE_REPETITIONS = "trainingExerciseRepetitions"
            const val TRAINING_TIME_BETWEEN_SETS = "trainingTimeBetweenSets"
            const val TRAINING_TOTAL_TIME = "trainingTotalTime"
        }
    }

    class ExerciseColumns {
        companion object {
            const val EXERCISE_NAME = "exerciseName"
            const val EXERCISE_IMAGE = "exerciseGroup"
            const val EXERCISE_GROUP = "exerciseImage"
        }
    }
}