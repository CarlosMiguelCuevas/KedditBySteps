package com.nearsoft.ccuevas.kedditbysteps.commons.utils

import android.support.test.espresso.IdlingResource

/**
 * Created by ccuevas on 12/11/17.
 */
object EspressoIdlingResource {

    private val RESOURCE = "GLOBAL"

    var mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

    fun getIdlingResource(): IdlingResource {
        return mCountingIdlingResource
    }
}