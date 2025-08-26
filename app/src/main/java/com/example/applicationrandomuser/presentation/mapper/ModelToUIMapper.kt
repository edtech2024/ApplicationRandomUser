package com.example.applicationrandomuser.presentation.mapper

import com.example.applicationrandomuser.domain.mapper.Mapper
import com.example.applicationrandomuser.domain.model.ItemModel
import com.example.applicationrandomuser.presentation.uistate.ItemUI

class ModelToUIMapper : Mapper<ItemModel, ItemUI> {
    override fun map(from: ItemModel): ItemUI {
        return ItemUI(
            gender = from.gender,
            name = "$from.name.title"+ " " +"$from.name.first" + " " +"$from.name.last",
            location = "$from.location.country"+ " " + "$from.location.city",
            email = from.email,
            login = from.login.username,
            dob = from.dob.age,
            registered = from.registered.date,
            phone = from.phone,
            cell = from.cell,
            id = "$from.id.name"+ " " + "$from.id.value",
            picture = from.picture.medium,
            nat = from.nat
        )
    }
}