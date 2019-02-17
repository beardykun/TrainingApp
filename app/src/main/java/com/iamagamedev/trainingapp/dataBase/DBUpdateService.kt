package com.iamagamedev.trainingapp.dataBase

import android.app.IntentService
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.iamagamedev.trainingapp.dataBase.objects.ExerciseObject

/*
class DBUpdateService : IntentService(TAG) {

    companion object {
        private val TAG = DBUpdateService::class.java.simpleName
        val ACTION_INSERT = "$TAG.INSERT"
        val ACTION_INSERT_TRAINING = "$TAG.INSERT_TRAINING"
        val ACTION_UPDATE = "$TAG.UPDATE"
        val ACTION_DELETE = "$TAG.DELETE"

        val EXTRA_VALUES = "$TAG.ContentValues"

        fun insertNewExercise(context: Context, contentValues: ContentValues) {
            val intent = Intent(context, DBUpdateService::class.java)
            intent.action = ACTION_INSERT
            intent.putExtra(EXTRA_VALUES, contentValues)
            context.startService(intent)
        }

        fun insertNewTraining(context: Context, exerciseObject: ExerciseObject) {
            val intent = Intent(context, DBUpdateService::class.java)
            intent.action = ACTION_INSERT_TRAINING
            intent.putExtra(EXTRA_VALUES, exerciseObject)
            context.startService(intent)
        }

        fun updateTraining(context: Context, uri: Uri, contentValues: ContentValues) {
            val intent = Intent(context, DBUpdateService::class.java)
            intent.action = ACTION_UPDATE
            intent.data = uri
            intent.putExtra(EXTRA_VALUES, contentValues)
            context.startService(intent)
        }

        fun deleteTraining(context: Context, uri: Uri) {
            val intent = Intent(context, DBUpdateService::class.java)
            intent.action = ACTION_DELETE
            intent.data = uri
            context.startService(intent)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        when {
            ACTION_INSERT == intent?.action -> {
                val values = intent.getParcelableExtra<ExerciseObject>(EXTRA_VALUES)
                performInsert(values)
            }
            ACTION_INSERT_TRAINING == intent?.action -> {
                val values = intent.getParcelableExtra<ExerciseObject>(EXTRA_VALUES)
                performInsertInTraining(values)
            }
            ACTION_UPDATE == intent?.action -> {
                val values = intent.getParcelableExtra<ExerciseObject>(EXTRA_VALUES)
                performUpdate(intent.data!!, values)
            }
            ACTION_DELETE == intent?.action -> performDelete(intent.data!!)
        }
    }

    private fun performDelete(data: Uri) {
        val count = contentResolver.delete(data, null, null)
        Log.d(TAG, "Deleted $count trainings")
    }

    private fun performUpdate(data: Uri, values: ContentValues?) {
        val count = contentResolver.update(data, values, null, null)
        Log.d(TAG, "Updated $count training items")
    }

    private fun performInsert(values: ContentValues?) {
        if (contentResolver.insert(DatabaseContract.CONTENT_URI_EXERCISE, values) != null) {
            Log.d(TAG, "Inserted new exercise")
        } else Log.w(TAG, "Error inserting new data")
    }

    private fun performInsertInTraining(values: ContentValues?) {
        if (contentResolver.insert(DatabaseContract.CONTENT_URI_TRAINING, values) != null) {
            Log.d(TAG, "Inserted new training")
        } else Log.w(TAG, "Error inserting new data")
    }
}*/
