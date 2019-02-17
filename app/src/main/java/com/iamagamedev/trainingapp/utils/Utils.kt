package com.iamagamedev.trainingapp.utils

import com.iamagamedev.trainingapp.app.ThisApplication
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        fun listToString(list: MutableList<String>): String {
            val stringBuilder = StringBuilder()
            for (i in 0 until list.size) {
                if (i != list.size - 1)
                    stringBuilder.append("${list[i]} , ")
                else
                    stringBuilder.append(list[i])
            }
            return stringBuilder.toString()
        }

        fun stringToList(string: String): MutableList<String> {
            return string.split(" , ").toMutableList()
        }

        fun getCurrentTime(): String {
            val calendar = Calendar.getInstance()
            val date = calendar.time
            val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.GERMANY)
            return dateFormat.format(date)
        }
    }
}