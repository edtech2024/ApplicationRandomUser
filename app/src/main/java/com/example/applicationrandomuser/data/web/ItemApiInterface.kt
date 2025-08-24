package com.example.applicationrandomuser.data.web

import com.example.applicationrandomuser.data.dto.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ItemApiInterface {

    @GET("api")
    suspend fun getItems(): Response<ApiResponse>

}