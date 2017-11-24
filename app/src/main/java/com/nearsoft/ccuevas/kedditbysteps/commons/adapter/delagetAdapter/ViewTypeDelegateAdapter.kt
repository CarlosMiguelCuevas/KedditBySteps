package com.nearsoft.ccuevas.kedditbysteps.commons.adapter.delagetAdapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.ViewType

/**
 * Created by ccuevas on 11/15/17.
 */
interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}