package com.example.applicationrandomuser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture(
    var large: String,
    var medium: String,
    var thumbnail: String
) : Parcelable

