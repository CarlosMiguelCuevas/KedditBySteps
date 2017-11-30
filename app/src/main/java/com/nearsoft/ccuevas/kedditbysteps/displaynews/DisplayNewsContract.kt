package com.nearsoft.ccuevas.kedditbysteps.displaynews

import com.nearsoft.ccuevas.kedditbysteps.commons.BaseContract
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNewsItem

/**
 * Created by ccuevas on 11/22/17.
 */
interface DisplayNewsContract {

    interface View : BaseContract.BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun showNoNews()

        fun hideNoNews()

        fun showNews(retrievedNewsList: List<RedditNewsItem>)

        fun hideNewsList()

        fun showError(error: Throwable)

        fun onScroll()

    }

    interface Presenter : BaseContract.BasePresenter {

        fun requestNews(requestCachedData: Boolean = false)

        fun dropView()

    }
}