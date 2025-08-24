package com.example.applicationrandomuser.data.converter

import androidx.room.TypeConverter
import com.example.applicationrandomuser.domain.model.Login
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoginConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromLogin(login: Login): String {
        return gson.toJson(login)
    }

    @TypeConverter
    fun toLogin(json: String): Login {
        return gson.fromJson(json, object : TypeToken<Login>() {}.type)
    }

}