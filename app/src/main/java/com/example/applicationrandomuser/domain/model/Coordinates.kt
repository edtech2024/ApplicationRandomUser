package com.example.applicationrandomuser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinates(
    var latitude: String,
    var longitude: String
) : Parcelable

