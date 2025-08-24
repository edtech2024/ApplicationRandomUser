package com.example.applicationrandomuser.data.dto

import com.example.applicationrandomuser.domain.model.*
import com.google.gson.annotations.SerializedName

data class ItemDTO public constructor(
    @SerializedName("gender")
    var gender: String,
    @SerializedName("name")
    var name: Name,
    @SerializedName("location")
    var location: Location,
    @SerializedName("email")
    var email: String,
    @SerializedName("login")
    var login: Login,
    @SerializedName("dob")
    var dob: Dob,
    @SerializedName("registered")
    var registered: Registered,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("cell")
    var cell: String,
    @SerializedName("id")
    var id: Id,
    @SerializedName("picture")
    var picture: Picture,
    @SerializedName("nat")
    var nat: String
) {
    companion object {
        operator fun invoke(
            gender: String,
            name: Name,
            location: Location,
            email: String,
            login: Login,
            dob: Dob,
            registered: Registered,
            phone: String,
            cell: String,
            id: Id,
            picture: Picture,
            nat: String
        ) = ItemDTO (
            gender = gender ?:"Gender",
            name = name ?: Name("","",""),
            location = location ?: Location(Street("",""),"","","","",Coordinates("",""),Timezone("","")),
            email = email ?: "Email",
            login = login ?: Login("","","","","","",""),
            dob = dob ?: Dob("",""),
            registered = registered ?: Registered("",""),
            phone = phone ?: "Phone",
            cell = cell ?: "Cell",
            id = id ?: Id("",""),
            picture = picture ?: Picture("","",""),
            nat = nat ?: "Nat"
        )
    }
}
