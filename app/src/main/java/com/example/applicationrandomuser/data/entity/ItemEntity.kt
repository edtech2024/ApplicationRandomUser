package com.example.applicationrandomuser.data.entity

import androidx.room.*
import com.example.applicationrandomuser.data.converter.*
import com.example.applicationrandomuser.domain.model.*

@Entity(tableName = "items")
data class ItemEntity public constructor(
    @ColumnInfo(name = "gender")
    var gender: String,
    @TypeConverters(NameConverter::class)
    @ColumnInfo(name = "name")
    var name: Name,
    @TypeConverters(LocationConverter::class, StreetConverter::class, CoordinatesConverter::class, TimezoneConverter::class)
    @ColumnInfo(name = "location")
    var location: Location,
    @ColumnInfo(name = "email")
    var email: String,
    @TypeConverters(LoginConverter::class)
    @ColumnInfo(name = "login")
    var login: Login,
    @TypeConverters(DobConverter::class)
    @ColumnInfo(name = "dob")
    var dob: Dob,
    @TypeConverters(RegisteredConverter::class)
    @ColumnInfo(name = "registered")
    var registered: Registered,
    @ColumnInfo(name = "phone")
    var phone: String,
    @ColumnInfo(name = "cell")
    var cell: String,
    @TypeConverters(IdConverter::class)
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Id,
    @TypeConverters(PictureConverter::class)
    @ColumnInfo(name = "picture")
    var picture: Picture,
    @ColumnInfo(name = "nat")
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
        ) = ItemEntity (
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