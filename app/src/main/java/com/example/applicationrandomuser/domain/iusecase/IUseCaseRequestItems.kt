package com.example.applicationrandomuser.domain.iusecase

import com.example.applicationrandomuser.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow

interface IUseCaseRequestItems {
    suspend operator fun invoke(): Flow<List<ItemModel>>
}