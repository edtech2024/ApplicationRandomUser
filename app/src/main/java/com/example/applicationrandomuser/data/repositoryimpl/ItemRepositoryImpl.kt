package com.example.applicationrandomuser.data.repositoryimpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.applicationrandomuser.data.utils.HandlerApiResponses
import com.example.applicationrandomuser.domain.model.ItemModel
import com.example.applicationrandomuser.data.database.ItemDao
import com.example.applicationrandomuser.data.mapper.ItemDTOTOModelMapper
import com.example.applicationrandomuser.data.mapper.ItemEntityToModelMapper
import com.example.applicationrandomuser.data.mapper.ModelToDTOMapper
import com.example.applicationrandomuser.data.mapper.ModelToEntityMapper
import com.example.applicationrandomuser.data.utils.ApiResult
import com.example.applicationrandomuser.data.web.ItemApiInterface
import com.example.applicationrandomuser.domain.irepository.IItemRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(val itemDao: ItemDao,
                                             val itemApiService: ItemApiInterface,
                                             val dispatcher: CoroutineDispatcher,
                                             val refreshIntervalMs: Long,
                                             val modelToEntity: ModelToEntityMapper,
                                             val modelToDTO: ModelToDTOMapper,
                                             val entityToModel: ItemEntityToModelMapper,
                                             val dtoToModel: ItemDTOTOModelMapper
) : IItemRepository {

    var errorMessage: Flow<String> = MutableLiveData("").asFlow().flowOn(dispatcher)
    override var itemList: Flow<List<ItemModel>> = flow<List<ItemModel>> {
        while(true) {
            val response = HandlerApiResponses.safeApiCall(dispatcher) { itemApiService.getItems() }
            var items: List<ItemModel> = listOf()
            when (response) {
                is ApiResult.Success -> items = dtoToModel.mapAll(response.data!!.results)
                is ApiResult.Error -> errorMessage = MutableLiveData(response.message!!).asFlow()
            }
            emit(items)
            delay(refreshIntervalMs) // Suspends the coroutine for some time
        }
    }.flowOn(dispatcher)

    override suspend fun insertItem(item: ItemModel): Long {
        return itemDao.insert(modelToEntity.map(item))
    }

    override suspend fun insertItems(items: List<ItemModel>) {
        itemDao.insertItems(modelToEntity.mapAll(items))
    }

    override suspend fun updateItem(item: ItemModel): Int {
        return itemDao.update(modelToEntity.map(item))
    }

    override suspend fun updateItems(items: List<ItemModel>) {
        itemDao.updateItems(modelToEntity.mapAll(items))
    }

    override suspend fun deleteItems() {
        itemDao.deleteAll()
    }

    override fun queryItemsfromDatabase(): Flow<List<ItemModel>> {
        return itemDao.getAllItems().map { entityToModel.mapAll(it) }
    }

    override suspend fun getCountRows(): Int {
        return itemDao.getCount()
    }

    override suspend fun requestItem(): Flow<List<ItemModel>> {
        val response = HandlerApiResponses.safeApiCall(dispatcher) { itemApiService.getItems() }
        when (response) {
            is ApiResult.Success -> itemList = MutableLiveData(dtoToModel.mapAll(response.data!!.results)).asFlow()
            is ApiResult.Error -> errorMessage = MutableLiveData(response.message!!).asFlow()
        }
        return itemList
    }

}