package com.example.applicationrandomuser.presentation.di

import android.os.Bundle
import com.example.applicationrandomuser.domain.*
import com.example.applicationrandomuser.domain.irepository.IItemRepository
import com.example.applicationrandomuser.domain.iusecase.*
import com.example.applicationrandomuser.presentation.fragment.DetailFragment
import com.example.applicationrandomuser.presentation.viewmodel.DetailViewModel
import com.example.applicationrandomuser.presentation.viewmodel.ListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {

    @Singleton
    @Provides
    fun provideUseCaseCreateImpl(repository: IItemRepository): IUseCaseCreateItem = UseCaseCreateItemImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseUpdateImpl(repository: IItemRepository): IUseCaseUpdateItem = UseCaseUpdateItemImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseQueryLocalItemsImpl(repository: IItemRepository): IUseCaseQueryLocalItems = UseCaseQueryLocalItemsImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseRequestItemsImpl(repository: IItemRepository): IUseCaseRequestItems = UseCaseRequestItemsImpl(repository)

    @Singleton
    @Provides
    fun provideUseCaseRefreshItemsImpl(repository: IItemRepository): IUseCaseRefreshItems = UseCaseRefreshItemsImpl(repository)

    @Provides
    fun provideListViewModel(useCaseCreateItem: IUseCaseCreateItem,
                             useCaseUpdateItem: IUseCaseUpdateItem,
                             useCaseRequestItems: IUseCaseRequestItems,
                             useCaseQueryLocalItems: IUseCaseQueryLocalItems,
                             useCaseRefreshItems: IUseCaseRefreshItems
    ): ListViewModel = ListViewModel(
        useCaseCreateItem,
        useCaseUpdateItem,
        useCaseRequestItems,
        useCaseQueryLocalItems,
        useCaseRefreshItems
    )

    @Provides
    fun provideDetailViewModel(bundle: Bundle?, context: DetailFragment.OnItemCloseListener): DetailViewModel = DetailViewModel(bundle, context)

}