package com.example.applicationrandomuser.domain

import com.example.applicationrandomuser.domain.model.ItemModel
import com.example.applicationrandomuser.domain.irepository.IItemRepository
import com.example.applicationrandomuser.domain.iusecase.IUseCaseRequestItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseRequestItemsImpl @Inject constructor(val repository: IItemRepository) : IUseCaseRequestItems {
    override suspend fun invoke(): Flow<List<ItemModel>> {
        return repository.requestItem()
    }
}