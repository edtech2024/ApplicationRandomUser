package com.example.applicationrandomuser.data.converter

import androidx.room.TypeConverter
import com.example.applicationrandomuser.domain.model.Registered
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RegisteredConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromRegistered(registered: Registered): String {
        return gson.toJson(registered)
    }

    @TypeConverter
    fun toRegistered(json: String): Registered {
        return gson.fromJson(json, object : TypeToken<Registered>() {}.type)
    }

}