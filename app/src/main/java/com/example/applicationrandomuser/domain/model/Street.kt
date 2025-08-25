package com.example.applicationrandomuser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Street(
    var number: String,
    var name: String,
): Parcelable
