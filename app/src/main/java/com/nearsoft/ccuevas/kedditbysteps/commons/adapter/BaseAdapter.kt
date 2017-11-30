package com.nearsoft.ccuevas.kedditbysteps.commons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nearsoft.ccuevas.kedditbysteps.commons.adapter.delagetAdapter.ViewTypeDelegateAdapter

/**
 * Created by ccuevas on 11/15/17.
 */
abstract class BaseAdapter<T : ViewType> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: ArrayList<ViewType>
    private val loadingItem: ViewType by lazy {
        object : ViewType {
            override fun getViewType() = AdapterConstants.LOADING
        }
    }

    init {
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = getAdapter(viewType).onCreateViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getAdapter(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }


    fun addItem(itemList: List<T>) {
        val initPosition = items.size - 1
        items.addAll(initPosition, itemList)
        notifyItemRangeInserted(initPosition, itemList.size)
    }

    fun clearAndroidItems(itemList: List<T>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(itemList)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getItem(): List<T> {
        return items
                .filter { item -> item.getViewType() == AdapterConstants.NEWS }
                .map { item -> item as T }
    }


    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

    protected abstract fun getAdapter(typeAdapter: Int): ViewTypeDelegateAdapter
}