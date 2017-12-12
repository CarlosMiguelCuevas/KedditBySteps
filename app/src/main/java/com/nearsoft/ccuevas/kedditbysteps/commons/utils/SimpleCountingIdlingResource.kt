package com.nearsoft.ccuevas.kedditbysteps.commons.utils

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.IdlingResource.ResourceCallback
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by ccuevas on 12/12/17.
 */
class SimpleCountingIdlingResource(private val mResourceName: String) : IdlingResource {

    private val mCounter = AtomicInteger(0)
    @Volatile private var mResourceCallback: ResourceCallback? = null

    override fun getName(): String {
        return mResourceName
    }

    override fun isIdleNow(): Boolean {
        return mCounter.get() == 0
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback?) {
        mResourceCallback = callback
    }

    fun increment() {
        mCounter.getAndIncrement()
    }

    fun decrement() {
        var counterVal = mCounter.decrementAndGet()

        if (counterVal == 0) {
            mResourceCallback?.onTransitionToIdle()
        }

        if (counterVal < 0) {
            throw IllegalArgumentException("Counter gas been corrupted")
        }
    }
}