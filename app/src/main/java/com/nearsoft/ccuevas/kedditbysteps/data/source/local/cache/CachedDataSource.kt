package com.nearsoft.ccuevas.kedditbysteps.data.source.local.cache

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews

/**
 * Created by ccuevas on 12/10/17.
 */
class CachedDataSource : CachedContract {

    private lateinit var mCachedNews: RedditNews

    init {
        mCachedNews = RedditNews("", "", ArrayList())
    }

    override fun getNews(): RedditNews? {
        return mCachedNews
    }

    override fun saveNews(news: RedditNews) {
        mCachedNews.after = news.after
        mCachedNews.before = news.before
        mCachedNews.news.addAll(news.news)
    }

}
