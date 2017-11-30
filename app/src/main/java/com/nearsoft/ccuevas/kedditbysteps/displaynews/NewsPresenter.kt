package com.nearsoft.ccuevas.kedditbysteps.displaynews

import com.nearsoft.ccuevas.kedditbysteps.commons.RxBasePresenter
import com.nearsoft.ccuevas.kedditbysteps.data.source.RepositoryDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ccuevas on 11/22/17.
 */
class NewsPresenter(private val newsRepository: RepositoryDataSource, private var newsView: DisplayNewsContract.View?) : RxBasePresenter(), DisplayNewsContract.Presenter {

    override
    fun requestNews(requestCachedData: Boolean) {

        if (!requestCachedData) {
            newsRepository.setRefreshNews()
        }

        val subscription = newsRepository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            newsView?.showNews(retrievedNews.news)
                        },
                        { error ->
                            newsView?.showError(error)
                        })
        subscriptions.add(subscription)
    }

    override fun dropView() {
        newsView = null
    }

}