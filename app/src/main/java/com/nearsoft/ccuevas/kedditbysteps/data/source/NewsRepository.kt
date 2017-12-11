package com.nearsoft.ccuevas.kedditbysteps.data.source

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import com.nearsoft.ccuevas.kedditbysteps.data.source.local.cache.CachedContract
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/17/17.
 */
class NewsRepository(private val mCache: CachedContract, private val mApi: BaseDataSource) : RepositoryDataSource {

    private var mIsCacheDirty: Boolean = false

    override
    fun getNews(after: String, limit: String): Observable<RedditNews> {
        if (mIsCacheDirty) {
            mIsCacheDirty = false
            return mApi.getNews(mCache.getNews()?.after ?: "", limit).doOnNext { news -> mCache.saveNews(news) }
        } else {
            return Observable.just(mCache.getNews())
        }
    }

    override fun setRefreshNews() {
        mIsCacheDirty = true
    }

}