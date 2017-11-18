package com.nearsoft.ccuevas.kedditbysteps.api

import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ccuevas on 11/17/17.
 */
interface ReditApi {
    @GET("top.json")
    fun getTop(@Query("after") after: String, @Query("limit") limit: String): Observable<RedditNewsResponse>
}