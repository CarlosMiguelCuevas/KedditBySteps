package com.nearsoft.ccuevas.kedditbysteps.data.source.local.cache

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import io.reactivex.Observable

/**
 * Created by ccuevas on 12/10/17.
 */
interface CachedContract {

    fun getNews(): RedditNews?

    fun saveNews(news: RedditNews)

}