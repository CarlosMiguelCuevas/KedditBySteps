package com.nearsoft.ccuevas.kedditbysteps.features.news

import com.nearsoft.ccuevas.kedditbysteps.api.RestApi
import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNews
import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNewsItem
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/17/17.
 */
class NewsManager(private val api: RestApi = RestApi()) {

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {

        return api.getNews(after, limit)
                .map {response -> response.data}
                .map { redditNews -> RedditNews(redditNews.after ?: "", redditNews.before ?: "", redditNews.children.map { newsItem -> with(newsItem.data) {
                            RedditNewsItem(author, title, num_comments, created, thumbnail, url)
                        }
                    })
                }
    }
}