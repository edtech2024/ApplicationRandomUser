package com.example.applicationrandomuser.data.converter

import androidx.room.TypeConverter
import com.example.applicationrandomuser.domain.model.Coordinates
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CoordinatesConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromCoordinates(coordinates: Coordinates): String {
        return gson.toJson(coordinates)
    }

    @TypeConverter
    fun toLocation(json: String): Coordinates {
        return gson.fromJson(json, object : TypeToken<Coordinates>() {}.type)
    }

}