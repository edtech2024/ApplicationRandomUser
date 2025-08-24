package com.example.applicationrandomuser.data.converter

import androidx.room.TypeConverter
import com.example.applicationrandomuser.domain.model.Id
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IdConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromId(id: Id): String {
        return gson.toJson(id)
    }

    @TypeConverter
    fun toId(json: String): Id {
        return gson.fromJson(json, object : TypeToken<Id>() {}.type)
    }

}