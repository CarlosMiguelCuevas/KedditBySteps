package com.nearsoft.ccuevas.kedditbysteps.displaynews

import com.nearsoft.ccuevas.kedditbysteps.commons.RxBasePresenter
import com.nearsoft.ccuevas.kedditbysteps.commons.schedulers.SchedulerProvider
import com.nearsoft.ccuevas.kedditbysteps.data.source.RepositoryDataSource

/**
 * Created by ccuevas on 11/22/17.
 */
class NewsPresenter(private val newsRepository: RepositoryDataSource, private var newsView: DisplayNewsContract.View?, schedulers: SchedulerProvider) : RxBasePresenter(schedulers), DisplayNewsContract.Presenter {

    override
    fun requestNews(requestCachedData: Boolean) {

        if (!requestCachedData) {
            newsRepository.setRefreshNews()
        }

        val subscription = newsRepository.getNews()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(
                        { retrievedNews ->
                            newsView?.showNews(retrievedNews.news)
                        },
                        { error ->
                            newsView?.showError(error)
                        })
        subscribe(subscription)
    }

    override fun dropView() {
        newsView = null
    }

}