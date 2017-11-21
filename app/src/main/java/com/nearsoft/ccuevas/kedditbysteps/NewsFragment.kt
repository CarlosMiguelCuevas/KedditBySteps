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
import javax.inject.Inject

class NewsFragment : RxBaseFragment() {

    companion object {
        private val KEY_REDDIT_NEWS = "KEY_REDDIT_NEWS"
    }

    @Inject
    lateinit var newsManager: NewsManager

    private val newsList by lazy { news_list }

    private var redditNews: RedditNews? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val linearLayout = LinearLayoutManager(context)

        newsList.apply {
            setHasFixedSize(true)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (newsList.adapter as NewsAdapter).clearAndroidNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val news = (newsList.adapter as NewsAdapter).getNews()

        if (redditNews != null && news.size > 0) {
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
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
