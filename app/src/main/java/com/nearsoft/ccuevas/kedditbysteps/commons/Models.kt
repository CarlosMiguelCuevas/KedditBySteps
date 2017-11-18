package com.nearsoft.ccuevas.kedditbysteps.commons

import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.AdapterConstants
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.ViewType

/**
 * Created by ccuevas on 11/15/17.
 */
data class RedditNewsItem(val author: String, val title: String, val numComments: Int, val created: Long, val thumbnail: String, val url: String) : ViewType {
    override fun getViewType() = AdapterConstants.NEWS
}

data class RedditNewsResponse(val data: RedditDataResponse)

data class RedditDataResponse(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
)

data class RedditChildrenResponse(val data: RedditNewsDataResponse)

data class RedditNewsDataResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
)