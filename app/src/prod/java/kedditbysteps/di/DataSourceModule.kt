package com.nearsoft.ccuevas.kedditbysteps.di

import com.nearsoft.ccuevas.kedditbysteps.commons.schedulers.SchedulerProvider
import com.nearsoft.ccuevas.kedditbysteps.commons.schedulers.SchedulerProviderProdImpl
import com.nearsoft.ccuevas.kedditbysteps.data.source.BaseDataSource
import com.nearsoft.ccuevas.kedditbysteps.data.source.NewsRepository
import com.nearsoft.ccuevas.kedditbysteps.data.source.RepositoryDataSource
import com.nearsoft.ccuevas.kedditbysteps.data.source.remote.NewsRemoteDataSource
import com.nearsoft.ccuevas.kedditbysteps.data.source.remote.endpoints.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by ccuevas on 11/20/17.
 */

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi {
        return retrofit.create(RedditApi::class.java)
    }

    @Provides
    @Singleton
    @Named("RemoteNewsData")
    fun provideRestApi(redditApi: RedditApi): BaseDataSource {
        return NewsRemoteDataSource(redditApi)
    }

    @Provides
    @Singleton
    @Named("RepositoryDataSource")
    fun provideNewsManager(@Named("RemoteNewsData") api: BaseDataSource): RepositoryDataSource {
        return NewsRepository(api)
    }

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerProvider {
        return SchedulerProviderProdImpl()
    }

}