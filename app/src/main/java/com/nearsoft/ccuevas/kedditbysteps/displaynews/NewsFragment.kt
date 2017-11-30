package com.nearsoft.ccuevas.kedditbysteps.displaynews

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nearsoft.ccuevas.kedditbysteps.R
import com.nearsoft.ccuevas.kedditbysteps.commons.InfiniteScrollListener
import com.nearsoft.ccuevas.kedditbysteps.commons.extencions.inflate
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNewsItem
import com.nearsoft.ccuevas.kedditbysteps.displaynews.adapter.NewsAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.news_fragment.*
import javax.inject.Inject
import javax.inject.Named

class NewsFragment : DaggerFragment(), DisplayNewsContract.View {

    companion object {
        private val KEY_REDDIT_NEWS = "KEY_REDDIT_NEWS"
        private val TAG = NewsFragment::class.java.simpleName

    }

    @field:[Inject Named("NewListPresenter")]
    lateinit var mPresenter: DisplayNewsContract.Presenter

    //views
    private val newsList by lazy { news_list }
    private val noNewsView by lazy { no_news_view }
    private val progressBar by lazy { progressbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
        initAdapter()
        mPresenter.requestNews(savedInstanceState != null)
    }

    private fun setAdapter() {
        val linearLayout = LinearLayoutManager(context)

        with(newsList) {
            setHasFixedSize(true)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ mPresenter.requestNews() }, linearLayout))
        }

    }

    private fun initAdapter() {
        if (newsList.adapter == null) {
            newsList.adapter = NewsAdapter()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.dropView()
    }

    override fun showNews(retrievedNewsList: List<RedditNewsItem>) {
        (newsList.adapter as NewsAdapter).addNews(retrievedNewsList)
        newsList.visibility = View.VISIBLE
        hideLoading()
    }

    override fun hideNewsList() {
        newsList.visibility = View.GONE
    }

    override fun showError(error: Throwable) {
        Log.e(TAG, error.message, error)
        Snackbar.make(view!!, getString(R.string.error_message_get_news), Snackbar.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showNoNews() {
        noNewsView.visibility = View.VISIBLE
    }

    override fun hideNoNews() {
        noNewsView.visibility = View.GONE
    }

    override fun onScroll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
