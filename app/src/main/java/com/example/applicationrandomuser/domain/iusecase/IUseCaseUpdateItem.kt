package com.example.applicationrandomuser.domain.iusecase

import com.example.applicationrandomuser.domain.model.ItemModel

interface IUseCaseUpdateItem {
    suspend operator fun invoke(item: ItemModel): Int
}