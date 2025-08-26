package com.example.applicationrandomuser.presentation.uistate

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemUI public constructor(
    var gender: String,
    var name: String,
    var location: String,
    var email: String,
    var login: String,
    var dob: String,
    var registered: String,
    var phone: String,
    var cell: String,
    var id: String,
    var picture: String,
    var nat: String
) : Parcelable {
    companion object {
        operator fun invoke(
            gender: String,
            name: String,
            location: String,
            email: String,
            login: String,
            dob: String,
            registered: String,
            phone: String,
            cell: String,
            id: String,
            picture: String,
            nat: String
        ) = ItemUI(
            gender = gender ?: "Gender",
            name = name ?: "Name",
            location = location ?: "Location",
            email = email ?: "Email",
            login = login ?: "Login",
            dob = dob ?: "Dob",
            registered = registered ?: "Registered",
            phone = phone ?: "Phone",
            cell = cell ?: "Cell",
            id = id ?: "Id",
            picture = picture ?: "Picture",
            nat = nat ?: "Nat"
        )
    }
}