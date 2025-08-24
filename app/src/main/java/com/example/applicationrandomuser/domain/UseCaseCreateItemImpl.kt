package com.example.applicationrandomuser.domain

import com.example.applicationrandomuser.domain.model.ItemModel
import com.example.applicationrandomuser.domain.irepository.IItemRepository
import com.example.applicationrandomuser.domain.iusecase.IUseCaseCreateItem
import javax.inject.Inject

class UseCaseCreateItemImpl @Inject constructor(val repository: IItemRepository) : IUseCaseCreateItem {
    override suspend fun invoke(item: ItemModel): Int {
        return repository.insertItem(item).toInt()
    }
}