package com.nearsoft.ccuevas.kedditbysteps.displaynews.di

import com.nearsoft.ccuevas.kedditbysteps.data.source.NewsDataSource
import com.nearsoft.ccuevas.kedditbysteps.data.source.NewsManager
import com.nearsoft.ccuevas.kedditbysteps.data.source.remote.RestApi
import com.nearsoft.ccuevas.kedditbysteps.data.source.remote.retrofit.RedditApi
import com.nearsoft.ccuevas.kedditbysteps.di.scopes.FragmentScope
import com.nearsoft.ccuevas.kedditbysteps.displaynews.DisplayNewsContract
import com.nearsoft.ccuevas.kedditbysteps.displaynews.NewsPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by ccuevas on 11/20/17.
 */
@Module
class NewsListModule {

    @Provides
    @FragmentScope
    fun provideRedditApi(retrofit: Retrofit): RedditApi {
        return retrofit.create(RedditApi::class.java)
    }

    @Provides
    @FragmentScope
    fun provideRestApi(redditApi: RedditApi): RestApi {
        return RestApi(redditApi)
    }

    @Provides
    @FragmentScope
    @Named("NewsDataSource")
    fun provideNewsManager(api: RestApi): NewsDataSource {
        return NewsManager(api)
    }

    @Provides
    @FragmentScope
    @Named("NewListPresenter")
    fun providePresenter(@Named("NewsDataSource") dataSource: NewsDataSource): DisplayNewsContract.Presenter {
        return NewsPresenter(dataSource)
    }

}