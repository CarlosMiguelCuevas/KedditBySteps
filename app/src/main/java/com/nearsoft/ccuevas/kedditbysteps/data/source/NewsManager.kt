package com.nearsoft.ccuevas.kedditbysteps.data.source

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNewsItem
import com.nearsoft.ccuevas.kedditbysteps.data.source.remote.RestApi
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/17/17.
 */
class NewsManager(private val api: RestApi): NewsDataSource {

    override
    fun getNews(after: String, limit: String): Observable<RedditNews> {
        return api.getNews(after, limit)
                .map { response -> response.data }
                .map { redditNews ->
                    RedditNews(redditNews.after ?: "", redditNews.before ?: "", redditNews.children.map { newsItem ->
                        with(newsItem.data) {
                            RedditNewsItem(author, title, num_comments, created, thumbnail, url)
                        }
                    })
                }
    }
}