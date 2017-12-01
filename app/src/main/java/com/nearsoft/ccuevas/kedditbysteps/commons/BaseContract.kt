package com.nearsoft.ccuevas.kedditbysteps.commons

import io.reactivex.disposables.Disposable

/**
 * Created by ccuevas on 11/22/17.
 */
interface BaseContract {

    interface BasePresenter {

        fun subscribe(subscription: Disposable)

        fun unSubscribe()

    }

    interface BaseView<T> {

    }

}