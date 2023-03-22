package com.cotemustis.myrecipes.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal class Converters {
    @TypeConverter
    fun listToString(list: List<String>) = Gson().toJson(list)

    @TypeConverter
    fun stringToList(string: String): List<String> =
        Gson().fromJson(string, object : TypeToken<List<String>>() {}.type)
}