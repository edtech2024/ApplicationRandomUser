package com.example.applicationrandomuser.presentation.viewmodel

import androidx.lifecycle.*
import com.example.applicationrandomuser.domain.iusecase.*
import com.example.applicationrandomuser.domain.model.ItemModel
import com.example.applicationrandomuser.presentation.mapper.ModelToUIMapper
import com.example.applicationrandomuser.presentation.uistate.ItemUI
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListViewModel @Inject constructor(
    val useCaseCreateItem: IUseCaseCreateItem,
    val useCaseUpdateItem: IUseCaseUpdateItem,
    val useCaseRequestItems: IUseCaseRequestItems,
    val useCaseQueryLocalItems: IUseCaseQueryLocalItems,
    val useCaseRefreshItems: IUseCaseRefreshItems,
    val ModelToUI: ModelToUIMapper
) : ViewModel() {

    var argSearch: String = ""

    private var liveData: LiveData<List<ItemUI>> = useCaseQueryLocalItems().map { ModelToUI.mapAll(it) }.asLiveData(Dispatchers.IO)

    private var mediator: MediatorLiveData<List<ItemUI>> = MediatorLiveData<List<ItemUI>>()

    var tempList: MutableList<ItemUI> = mutableListOf()

    var itemsList: LiveData<List<ItemUI>> = mediator

    init {
        loading()
    }

    private fun loading() {

        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                useCaseRequestItems()
                useCaseRefreshItems()
            }
        }

        mediator.addSource(liveData) { source ->
            tempList.addAll(source)
            mediator.setValue(tempList)
        }
    }

    fun applySortMethod() {
        sortByName()
    }

    fun applyFilterMethod() {
        filtrationByTitle(argSearch)
    }

    fun applyResetFilter() {
        argSearch = ""
        sortById()

        mediator.postValue(tempList)
    }

    fun sortById(){
        tempList.sortBy { item -> item.id }
        mediator.postValue(tempList)
    }

    fun sortByName() {
        tempList.sortBy { item -> item.name }
        mediator.postValue(tempList)
    }

    fun filtrationByTitle(name: String){
        var filteredList = tempList.filter { it.name.contains(name, ignoreCase = true) }
        mediator.postValue(filteredList)
    }

    fun clickButtonDeleteItem(itemModel: ItemModel) {
        viewModelScope.launch {
            withContext(NonCancellable) {

            }
        }
    }

    // Launching a new coroutine to insert the data in a non-blocking way
    private fun clickButtonCreateItem(item: ItemModel){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                useCaseCreateItem(item)
            }
        }
    }

    // Launching a new coroutine to update the data in a non-blocking way
    private fun clickButtonUpdateItem(item: ItemModel){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                useCaseUpdateItem(item)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                useCaseRequestItems()
                useCaseRefreshItems()
            }
        }
    }

}