package com.example.applicationrandomuser.domain.model

data class ItemModel public constructor(
    var gender: String,
    var name: Name,
    var location: Location,
    var email: String,
    var login: Login,
    var dob: Dob,
    var registered: Registered,
    var phone: String,
    var cell: String,
    var id: Id,
    var picture: Picture,
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
        ) = ItemModel (
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