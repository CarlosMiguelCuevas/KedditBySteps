@file:JvmName("ExtencionsUtil")

package com.nearsoft.ccuevas.kedditbysteps.commons.extencions

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.nearsoft.ccuevas.kedditbysteps.R
import com.squareup.picasso.Picasso

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImage(imageUrl: String) {

    if (!TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).isLoggingEnabled()
        Log.d("URL", imageUrl)
        Picasso.with(context).load(imageUrl).into(this)
    }
}