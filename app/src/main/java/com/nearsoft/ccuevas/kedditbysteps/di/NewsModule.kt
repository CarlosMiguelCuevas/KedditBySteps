package com.nearsoft.ccuevas.kedditbysteps.di

import com.nearsoft.ccuevas.kedditbysteps.api.RedditApi
import com.nearsoft.ccuevas.kedditbysteps.api.RestApi
import com.nearsoft.ccuevas.kedditbysteps.features.news.NewsManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by ccuevas on 11/20/17.
 */
@Module
class NewsModule {

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
    fun provideNewsManager(api: RestApi): NewsManager {
        return NewsManager(api)
    }

}