package com.nearsoft.ccuevas.kedditbysteps.features.news

import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNewsItem
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/17/17.
 */
class NewsManager {

    fun getNews(): Observable<List<RedditNewsItem>> {
        return Observable.create { subscriber ->

            val news = mutableListOf<RedditNewsItem>()
            for (i in 1..10) {
                news.add(RedditNewsItem(
                        "author$i",
                        "Title $i",
                        i,
                        1457207701L - i * 200,
                        "http://lorempixel.com/200/200/technics/$i",
                        "url"))
            }
            subscriber.onNext(news)
        }
    }
}