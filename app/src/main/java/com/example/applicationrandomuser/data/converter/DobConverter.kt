package com.example.applicationrandomuser.data.converter

import androidx.room.TypeConverter
import com.example.applicationrandomuser.domain.model.Dob
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DobConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromDob(dob: Dob): String {
        return gson.toJson(dob)
    }

    @TypeConverter
    fun toDob(json: String): Dob {
        return gson.fromJson(json, object : TypeToken<Dob>() {}.type)
    }

}