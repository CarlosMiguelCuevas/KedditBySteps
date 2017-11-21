package com.nearsoft.ccuevas.kedditbysteps.api

import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNewsResponse
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/17/17.
 */
class RestApi(private val redditApi: RedditApi) {

    fun getNews(after: String, limit: String): Observable<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}