package com.example.applicationrandomuser.data.converter

import androidx.room.TypeConverter
import com.example.applicationrandomuser.domain.model.Picture
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PictureConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromPicture(picture: Picture): String {
        return gson.toJson(picture)
    }

    @TypeConverter
    fun toPicture(json: String): Picture {
        return gson.fromJson(json, object : TypeToken<Picture>() {}.type)
    }

}