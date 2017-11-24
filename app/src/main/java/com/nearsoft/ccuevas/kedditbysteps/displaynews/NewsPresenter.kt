package com.nearsoft.ccuevas.kedditbysteps.displaynews

import com.nearsoft.ccuevas.kedditbysteps.commons.RxBasePresenter
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import com.nearsoft.ccuevas.kedditbysteps.data.source.NewsDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ccuevas on 11/22/17.
 */
class NewsPresenter(private val newsDataSource: NewsDataSource) : RxBasePresenter(), DisplayNewsContract.Presenter {

    private var redditNews: RedditNews? = null
    private var newsView: DisplayNewsContract.View? = null

    override
    fun requestNews() {
        val subscription = newsDataSource.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            redditNews = retrievedNews
                            newsView?.showNews(retrievedNews.news)
                        },
                        { error ->
                            newsView?.showError(error)
                        })
        subscriptions.add(subscription)
    }

    override fun setView(view: DisplayNewsContract.View) {
            newsView = view
    }

    override fun dropView() {
        newsView = null
    }

}