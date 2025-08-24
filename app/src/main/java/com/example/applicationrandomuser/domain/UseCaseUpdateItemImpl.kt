package com.example.applicationrandomuser.domain

import com.example.applicationrandomuser.domain.model.ItemModel
import com.example.applicationrandomuser.domain.irepository.IItemRepository
import com.example.applicationrandomuser.domain.iusecase.IUseCaseUpdateItem
import javax.inject.Inject

class UseCaseUpdateItemImpl @Inject constructor(val repository: IItemRepository) :
    IUseCaseUpdateItem {
    override suspend fun invoke(item: ItemModel): Int {
        return repository.updateItem(item)
    }
}