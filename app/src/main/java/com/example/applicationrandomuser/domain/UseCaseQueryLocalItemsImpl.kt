package com.example.applicationrandomuser.domain

import com.example.applicationrandomuser.domain.model.ItemModel
import com.example.applicationrandomuser.domain.irepository.IItemRepository
import com.example.applicationrandomuser.domain.iusecase.IUseCaseQueryLocalItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseQueryLocalItemsImpl @Inject constructor(val repository: IItemRepository) : IUseCaseQueryLocalItems {
    override fun invoke(): Flow<List<ItemModel>> {
        return repository.queryItemsfromDatabase()
    }
}