package com.nearsoft.ccuevas.kedditbysteps.commons.adapter

/**
 * Created by ccuevas on 11/15/17.
 */
class NewsAdapter : BaseAdapter() {
    override fun getAdapter(typeAdapter: Int): ViewTypeDelegateAdapter {
        if (typeAdapter == AdapterConstants.NEWS) {
            return NewsDelegateAdapter()
        } else {
            return LoadingDelegateAdapter()
        }
    }
}