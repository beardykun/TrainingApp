package com.iamagamedev.trainingapp.utils

import com.iamagamedev.trainingapp.app.Constants
import com.iamagamedev.trainingapp.app.MySharedPreferences
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        fun listToString(list: MutableList<String>): String {
            val stringBuilder = StringBuilder()
            for (i in 0 until list.size) {
                if (!i.equals("")) {
                    if (i != list.size - 1)
                        stringBuilder.append("${list[i]} , ")
                    else
                        stringBuilder.append(list[i])
                }
            }
            return stringBuilder.toString()
        }

        fun stringToList(string: String): MutableList<String> {
            return if (string != Constants.EMPTY_STRING && string != "")
                string.split(" , ").toMutableList()
            else
                mutableListOf(Constants.EMPTY_STRING)
        }

        fun getCurrentTime(): String {
            val calendar = Calendar.getInstance()
            val date = calendar.time
            val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.GERMANY)
            return dateFormat.format(date)
        }

        fun getCurrentTrainingList(): String {
            val trainingName = MySharedPreferences.getString(Constants.SAVE_TRAINING_NAME)
            return trainingName + Constants.SAVE_NEW_EXERCISE_LIST
        }
    }
}