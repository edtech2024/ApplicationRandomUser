package com.example.applicationrandomuser.domain

import com.example.applicationrandomuser.domain.irepository.IItemRepository
import com.example.applicationrandomuser.domain.iusecase.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class UseCaseRefreshItemsImpl @Inject constructor(val repository: IItemRepository) : IUseCaseRefreshItems {
    override suspend fun invoke() {
        if (repository.getCountRows() == 0) {
            repository.itemList.collect { it -> repository.insertItems(it) }
        }
        else {
            repository.itemList.collect { it -> repository.updateItems(it) }
        }
    }
}