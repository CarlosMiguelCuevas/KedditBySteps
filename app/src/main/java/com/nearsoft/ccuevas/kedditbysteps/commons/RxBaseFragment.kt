package com.nearsoft.ccuevas.kedditbysteps.commons

import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ccuevas on 11/17/17.
 */
open class RxBaseFragment : DaggerFragment() {

    protected var subscriptions = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
        subscriptions.clear()
    }
}