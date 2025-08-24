package com.example.applicationrandomuser.data.converter

import androidx.room.TypeConverter
import com.example.applicationrandomuser.domain.model.Street
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StreetConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromStreet(street: Street): String {
        return gson.toJson(street)
    }

    @TypeConverter
    fun toStreet(json: String): Street {
        return gson.fromJson(json, object : TypeToken<Street>() {}.type)
    }

}