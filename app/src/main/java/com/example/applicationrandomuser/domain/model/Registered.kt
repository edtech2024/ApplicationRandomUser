package com.example.applicationrandomuser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Registered(
    var date: String,
    var age: String,
) : Parcelable

