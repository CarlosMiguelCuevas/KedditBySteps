package com.nearsoft.ccuevas.kedditbysteps.commons

import com.nearsoft.ccuevas.kedditbysteps.commons.schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by ccuevas on 11/17/17.
 */
open class RxBasePresenter(protected val schedulers: SchedulerProvider) : BaseContract.BasePresenter {

    protected var subscriptions = CompositeDisposable()

    override fun subscribe(subscription: Disposable) {
        subscriptions.add(subscription)
    }

    override fun unSubscribe() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
        subscriptions.clear()
    }

}