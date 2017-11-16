package com.nearsoft.ccuevas.kedditbysteps.commons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nearsoft.ccuevas.kedditbysteps.R
import com.nearsoft.ccuevas.kedditbysteps.commons.extencions.inflate

/**
 * Created by ccuevas on 11/15/17.
 */
class LoadingDelegateAdapter : ViewtypeDelegateAdapter{
    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {}

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item_loading)) {}
}