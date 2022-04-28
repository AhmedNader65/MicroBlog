package com.najwa.task.di

import com.shahry.microblogging.data.api.DataApi
import com.shahry.microblogging.data.dataSource.DataRemoteDataSource
import com.shahry.microblogging.data.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDataRemoteDataSource(
        dataApi: DataApi,
    ) = DataRemoteDataSource(dataApi)

    @Provides
    @Singleton
    fun provideDataRepository(
        dataSource: DataRemoteDataSource,
    ) = DataRepository(dataSource)

}