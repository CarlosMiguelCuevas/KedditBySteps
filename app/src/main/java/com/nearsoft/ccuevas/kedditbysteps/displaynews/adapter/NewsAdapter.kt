package com.nearsoft.ccuevas.kedditbysteps.displaynews.adapter

import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.AdapterConstants
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.BaseAdapter
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.delagetAdapter.LoadingDelegateAdapter
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.delagetAdapter.ViewTypeDelegateAdapter
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNewsItem

/**
 * Created by ccuevas on 11/15/17.
 */
class NewsAdapter : BaseAdapter<RedditNewsItem>() {
    override fun getAdapter(typeAdapter: Int): ViewTypeDelegateAdapter {
        if (typeAdapter == AdapterConstants.NEWS) {
            return NewsDelegateAdapter()
        } else {
            return LoadingDelegateAdapter()
        }
    }
}