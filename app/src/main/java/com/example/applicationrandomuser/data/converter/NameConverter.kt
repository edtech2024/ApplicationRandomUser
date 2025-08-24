package com.example.applicationrandomuser.data.converter

import androidx.room.TypeConverter
import com.example.applicationrandomuser.domain.model.Name
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NameConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromLocation(name: Name): String {
        return gson.toJson(name)
    }

    @TypeConverter
    fun toLocation(json: String): Name {
        return gson.fromJson(json, object : TypeToken<Name>() {}.type)
    }

}