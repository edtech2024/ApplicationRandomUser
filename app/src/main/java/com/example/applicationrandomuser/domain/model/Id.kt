package com.example.applicationrandomuser.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Id(
    var name: String,
    var value: String
) : Parcelable

