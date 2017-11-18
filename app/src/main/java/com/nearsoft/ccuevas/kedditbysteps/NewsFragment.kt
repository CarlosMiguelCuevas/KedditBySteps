package com.nearsoft.ccuevas.kedditbysteps

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nearsoft.ccuevas.kedditbysteps.commons.InfiniteScrollListener
import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNews
import com.nearsoft.ccuevas.kedditbysteps.commons.RxBaseFragment
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.NewsAdapter
import com.nearsoft.ccuevas.kedditbysteps.commons.extencions.inflate
import com.nearsoft.ccuevas.kedditbysteps.features.news.NewsManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : RxBaseFragment() {

    private val newsList by lazy { news_list }
    private val newsManager by lazy { NewsManager() }
    private var redditNews: RedditNews? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val linearLayout = LinearLayoutManager(context)
        newsList.setHasFixedSize(true)
        newsList.layoutManager = linearLayout
        newsList.clearOnScrollListeners()
        newsList.addOnScrollListener(InfiniteScrollListener({requestNews()}, linearLayout))

        initAdapter()

        if (savedInstanceState == null) {
            requestNews()
        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            redditNews = retrievedNews
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        { error ->
                            Snackbar.make(newsList, error.message ?: "", Snackbar.LENGTH_SHORT).show()
                        })
        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if (newsList.adapter == null) {
            newsList.adapter = NewsAdapter()
        }
    }
}
