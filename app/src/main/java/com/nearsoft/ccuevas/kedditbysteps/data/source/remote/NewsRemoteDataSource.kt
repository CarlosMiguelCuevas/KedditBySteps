package com.nearsoft.ccuevas.kedditbysteps.data.source.remote

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNewsItem
import com.nearsoft.ccuevas.kedditbysteps.data.source.BaseDataSource
import com.nearsoft.ccuevas.kedditbysteps.data.source.remote.endpoints.RedditApi
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/17/17.
 */
class NewsRemoteDataSource(private val redditApi: RedditApi) : BaseDataSource {
    override fun getNews(after: String, limit: String): Observable<RedditNews> {
        return redditApi.getTop(after, limit)
                .map { response -> response.data }
                .map { redditNews ->
                    RedditNews(redditNews.after ?: "", redditNews.before ?: "", redditNews.children.map { newsItem ->
                        with(newsItem.data) {
                            RedditNewsItem(author, title, num_comments, created, thumbnail, url)
                        }
                    } as ArrayList<RedditNewsItem>)
                }
    }

}