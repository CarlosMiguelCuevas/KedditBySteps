package com.nearsoft.ccuevas.kedditbysteps.data.newsmodels

data class RedditNewsResponse(val data: RedditDataResponse)

data class RedditDataResponse(
        val children: ArrayList<RedditChildrenResponse>,
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