package com.example.applicationrandomuser.data.converter

import androidx.room.TypeConverter
import com.example.applicationrandomuser.domain.model.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocationConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromLocation(location: Location): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocation(json: String): Location {
        return gson.fromJson(json, object : TypeToken<Location>() {}.type)
    }

}