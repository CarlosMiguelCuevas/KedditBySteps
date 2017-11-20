package com.nearsoft.ccuevas.kedditbysteps.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.nearsoft.ccuevas.kedditbysteps.BuildConfig
import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNewsResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ccuevas on 11/17/17.
 */
@Singleton
class RestApi @Inject constructor(private val redditApi: RedditApi){

    fun getNews(after: String, limit: String): Observable<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}