package com.nearsoft.ccuevas.kedditbysteps.features.news

import com.nearsoft.ccuevas.kedditbysteps.api.RestApi
import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNewsItem
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/17/17.
 */
class NewsManager(private val api: RestApi = RestApi()) {

    fun getNews(limit: String = "10"): Observable<List<RedditNewsItem>> {

        return api.getNews("", limit)
                .map { response -> response.data.children }
                .map{ newsList ->
                    newsList.map { newsItem -> with(newsItem.data) {
                            RedditNewsItem(author, title, num_comments, created, thumbnail, url)
                        }
                    }
                }
    }
}