package com.nearsoft.ccuevas.kedditbysteps.data.source

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/23/17.
 */
interface NewsDataSource {
    fun getNews(after: String, limit: String = "10"): Observable<RedditNews>
}