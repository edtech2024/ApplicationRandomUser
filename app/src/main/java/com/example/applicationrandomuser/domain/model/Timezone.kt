package com.example.applicationrandomuser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Timezone(
    var offset: String,
    var description: String
) : Parcelable
