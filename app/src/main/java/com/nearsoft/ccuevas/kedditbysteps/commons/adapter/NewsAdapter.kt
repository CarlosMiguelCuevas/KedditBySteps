package com.nearsoft.ccuevas.kedditbysteps.commons.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nearsoft.ccuevas.kedditbysteps.R
import com.nearsoft.ccuevas.kedditbysteps.commons.RedditNewsItem
import com.nearsoft.ccuevas.kedditbysteps.commons.extencions.getFriendlyTime
import com.nearsoft.ccuevas.kedditbysteps.commons.extencions.inflate
import com.nearsoft.ccuevas.kedditbysteps.commons.extencions.loadImage

/**
 * Created by ccuevas on 11/15/17.
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewtypeDelegateAdapter>()
    private val loadingItem: ViewType by lazy {
        object : ViewType {
            override fun getViewType() = AdapterConstants.LOADING
        }
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = delegateAdapters.get(viewType).onCreateViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }


    fun addNews(news: List<RedditNewsItem>) {
        val initPosition = items.size - 1
        items.addAll(initPosition, news)
        notifyItemRangeInserted(initPosition, news.size)
    }

    fun clearAndroidNews(news: List<RedditNewsItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<RedditNewsItem> {
        return items
                .filter { item -> item.getViewType() == AdapterConstants.NEWS}
                .map { item -> item as RedditNewsItem }
    }


    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

}