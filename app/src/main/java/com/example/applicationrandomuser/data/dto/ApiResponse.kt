package com.example.applicationrandomuser.data.dto

import com.example.applicationrandomuser.data.dto.Info
import com.example.applicationrandomuser.data.dto.ItemDTO
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<ItemDTO>
)
