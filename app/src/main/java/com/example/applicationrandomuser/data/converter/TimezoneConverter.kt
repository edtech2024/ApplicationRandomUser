package com.example.applicationrandomuser.data.converter

import androidx.room.TypeConverter
import com.example.applicationrandomuser.domain.model.Timezone
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TimezoneConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromTimezone(timezone: Timezone): String {
        return gson.toJson(timezone)
    }

    @TypeConverter
    fun toTimezone(json: String): Timezone {
        return gson.fromJson(json, object : TypeToken<Timezone>() {}.type)
    }

}