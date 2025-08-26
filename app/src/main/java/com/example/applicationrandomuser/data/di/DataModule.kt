package com.example.applicationrandomuser.data.di

import android.content.Context
import com.example.applicationrandomuser.data.database.ItemDatabase
import com.example.applicationrandomuser.data.mapper.ItemDTOTOModelMapper
import com.example.applicationrandomuser.data.mapper.ItemEntityToModelMapper
import com.example.applicationrandomuser.data.mapper.ModelToDTOMapper
import com.example.applicationrandomuser.data.mapper.ModelToEntityMapper
import com.example.applicationrandomuser.data.repositoryimpl.ItemRepositoryImpl
import com.example.applicationrandomuser.data.web.ItemApiInterface
import com.example.applicationrandomuser.domain.irepository.IItemRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
class DataModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor)
            : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .retryOnConnectionFailure(true)
        .build()

    @Singleton
    @Provides
    fun provideBaseUrl(): String = "https://randomuser.me/"

    @Singleton
    @Provides
    fun provideAuthApiService(BASE_URL: String,
                              okHttpClient: OkHttpClient
    ): ItemApiInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ItemApiInterface::class.java)

    @Singleton
    @Provides
    fun provideDatabase(context: Context): ItemDatabase = ItemDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideRefreshIntervalMs(): Long = 50000

    @Singleton
    @Provides
    fun provideModelToEntityMapper(): ModelToEntityMapper = ModelToEntityMapper()

    @Singleton
    @Provides
    fun provideModelToDTOMapper(): ModelToDTOMapper = ModelToDTOMapper()

    @Singleton
    @Provides
    fun provideEntityToModelMapper(): ItemEntityToModelMapper = ItemEntityToModelMapper()

    @Singleton
    @Provides
    fun provideDTOToModelMapper(): ItemDTOTOModelMapper = ItemDTOTOModelMapper()

    @Singleton
    @Provides
    fun provideRepositoryImpl(database: ItemDatabase,
                              itemApiService: ItemApiInterface,
                              dispatcher: CoroutineDispatcher,
                              refreshIntervalMs: Long,
                              modelToEntity: ModelToEntityMapper,
                              modelToDTO: ModelToDTOMapper,
                              entityToModel: ItemEntityToModelMapper,
                              dtoToModel: ItemDTOTOModelMapper
    ): IItemRepository = ItemRepositoryImpl(
        database.itemDao(),
        itemApiService,
        dispatcher,
        refreshIntervalMs,
        modelToEntity,
        modelToDTO,
        entityToModel,
        dtoToModel
    )

}