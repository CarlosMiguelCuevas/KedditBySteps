package com.nearsoft.ccuevas.kedditbysteps.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.nearsoft.ccuevas.kedditbysteps.BuildConfig
import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNewsResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ccuevas on 11/17/17.
 */
class RestApi {
    private val redditApi: ReditApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        redditApi = retrofit.create(ReditApi::class.java)
    }

    fun getNews(after: String, limit: String): Observable<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}