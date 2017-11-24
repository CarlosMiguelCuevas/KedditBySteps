package com.nearsoft.ccuevas.kedditbysteps.displaynews.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nearsoft.ccuevas.kedditbysteps.R
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.ViewType
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.delagetAdapter.ViewTypeDelegateAdapter
import com.nearsoft.ccuevas.kedditbysteps.commons.extencions.getFriendlyTime
import com.nearsoft.ccuevas.kedditbysteps.commons.extencions.inflate
import com.nearsoft.ccuevas.kedditbysteps.commons.extencions.loadImage
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNewsItem
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by ccuevas on 11/15/17.
 */
class NewsDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = NewsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item)) {

        fun bind(item: RedditNewsItem) = with(itemView) {
            img_thumbnail.loadImage(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }
}