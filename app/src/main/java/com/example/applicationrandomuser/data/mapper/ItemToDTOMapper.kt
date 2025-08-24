package com.example.applicationrandomuser.data.mapper

import com.example.applicationrandomuser.data.dto.ItemDTO
import com.example.applicationrandomuser.domain.model.ItemModel

class ItemToDTOMapper : Mapper<ItemModel, ItemDTO> {
    override fun map(from: ItemModel): ItemDTO {
        return ItemDTO(
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