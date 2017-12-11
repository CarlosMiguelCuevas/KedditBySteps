package com.nearsoft.ccuevas.kedditbysteps.di

import com.nearsoft.ccuevas.kedditbysteps.data.source.BaseDataSource
import com.nearsoft.ccuevas.kedditbysteps.data.source.NewsRepository
import com.nearsoft.ccuevas.kedditbysteps.data.source.RepositoryDataSource
import dagger.Module
import dagger.Provides
import kedditbysteps.data.source.remote.MockedNewsRemoteDataSource
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by ccuevas on 11/20/17.
 */

@Module
class ApiModule {

    @Provides
    @Singleton
    @Named("RemoteNewsData")
    fun provideRestApi(): BaseDataSource {
        return MockedNewsRemoteDataSource()
    }

}