package com.nearsoft.ccuevas.kedditbysteps.data.newsmodels

import android.os.Parcel
import android.os.Parcelable
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.AdapterConstants
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.ViewType


data class RedditNewsItem(val author: String, val title: String, val numComments: Int, val created: Long, val thumbnail: String, val url: String) : ViewType, Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readLong(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(author)
        writeString(title)
        writeInt(numComments)
        writeLong(created)
        writeString(thumbnail)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RedditNewsItem> = object : Parcelable.Creator<RedditNewsItem> {
            override fun createFromParcel(source: Parcel): RedditNewsItem = RedditNewsItem(source)
            override fun newArray(size: Int): Array<RedditNewsItem?> = arrayOfNulls(size)
        }
    }

    override fun getViewType() = AdapterConstants.NEWS

}

