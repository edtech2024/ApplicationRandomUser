package com.example.applicationrandomuser.data.mapper

import com.example.applicationrandomuser.data.entity.ItemEntity
import com.example.applicationrandomuser.domain.mapper.Mapper
import com.example.applicationrandomuser.domain.model.ItemModel

class ItemEntityToModelMapper : Mapper<ItemEntity, ItemModel> {
    override fun map(from: ItemEntity): ItemModel {
        return ItemModel(
            gender = from.gender,
            name = from.name,
            location = from.location,
            email = from.email,
            login = from.login,
            dob = from.dob,
            registered = from.registered,
            phone = from.phone,
            cell = from.cell,
            id = from.id,
            picture = from.picture,
            nat = from.nat
        )
    }
}