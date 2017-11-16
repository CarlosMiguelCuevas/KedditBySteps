package com.nearsoft.ccuevas.kedditbysteps

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNewsItem
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.NewsAdapter
import com.nearsoft.ccuevas.kedditbysteps.commons.extencions.inflate
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : Fragment() {

    private val newsList by lazy { news_list }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsList.setHasFixedSize(true)
        newsList.layoutManager = LinearLayoutManager(context)

        initAdapter()

        if (savedInstanceState == null) {
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
            (newsList.adapter as NewsAdapter).addNews(news)
        }
    }

    private fun initAdapter() {
        if(newsList.adapter == null) {
            newsList.adapter = NewsAdapter()
        }
    }
}
