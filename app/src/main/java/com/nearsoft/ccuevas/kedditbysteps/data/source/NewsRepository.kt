package com.nearsoft.ccuevas.kedditbysteps.data.source

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/17/17.
 */
class NewsRepository(private val api: BaseDataSource) : RepositoryDataSource {

    private var mCachedNews: RedditNews? = null
    private var mIsCacheDirty: Boolean = true

    override
    fun getNews(after: String, limit: String): Observable<RedditNews> {
        if (mIsCacheDirty) {
            mIsCacheDirty = false
            return api.getNews(mCachedNews?.after ?: "", limit).doOnNext { news -> mCachedNews = news }
        } else {
            return Observable.just(mCachedNews)
        }
    }

    override fun setRefreshNews() {
        mIsCacheDirty = true
    }

}