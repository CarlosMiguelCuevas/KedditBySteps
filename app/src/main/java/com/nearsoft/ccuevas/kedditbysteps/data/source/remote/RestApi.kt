package com.nearsoft.ccuevas.kedditbysteps.data.source.remote

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNewsResponse
import com.nearsoft.ccuevas.kedditbysteps.data.source.remote.retrofit.RedditApi
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/17/17.
 */
class RestApi(private val redditApi: RedditApi) {

    fun getNews(after: String, limit: String): Observable<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}