package com.example.applicationrandomuser.presentation.mapper

import com.example.applicationrandomuser.domain.mapper.Mapper
import com.example.applicationrandomuser.domain.model.*
import com.example.applicationrandomuser.presentation.uistate.ItemUI

class UIToModelMapper : Mapper<ItemUI, ItemModel> {
    override fun map(from: ItemUI): ItemModel {
        return ItemModel(
            gender = from.gender,
            name = Name(from.name,"",""),
            location = Location(Street("",""),"","","from.location","", Coordinates("",""),Timezone("","")),
            email = from.email,
            login =Login("",from.login,"","","","",""),
            dob =  Dob("", from.dob),
            registered = Registered(from.registered,""),
            phone = from.phone,
            cell = from.cell,
            id = Id(from.id,""),
            picture = Picture("",from.picture,""),
            nat = from.nat
        )
    }
}