package com.nearsoft.ccuevas.kedditbysteps.data.newsmodels

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ccuevas on 11/15/17.
 */

data class RedditNews(var after: String, var before: String, var news: ArrayList<RedditNewsItem>) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            ArrayList<RedditNewsItem>().apply { source.readList(this, RedditNewsItem::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(after)
        writeString(before)
        writeList(news)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RedditNews> = object : Parcelable.Creator<RedditNews> {
            override fun createFromParcel(source: Parcel): RedditNews = RedditNews(source)
            override fun newArray(size: Int): Array<RedditNews?> = arrayOfNulls(size)
        }
    }
}
