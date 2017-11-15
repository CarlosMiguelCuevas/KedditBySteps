package com.nearsoft.ccuevas.kedditbysteps

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NewsFragment : Fragment() {

    private var newsList: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_news, container, false)
        newsList = view?.findViewById<RecyclerView>(R.id.news_list)
        newsList?.setHasFixedSize(true)
        newsList?.layoutManager = LinearLayoutManager(context)
        return view
    }


}
