package com.nearsoft.ccuevas.kedditbysteps.data.source.local.cache

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import io.reactivex.Observable

/**
 * Created by ccuevas on 12/10/17.
 */
class CachedDataSource : CachedContract {

    private var mCachedNews: RedditNews? = null

    override fun getNews(): RedditNews? {
        return mCachedNews
    }

    override fun saveNews(news: RedditNews) {
        mCachedNews = news
    }

}
