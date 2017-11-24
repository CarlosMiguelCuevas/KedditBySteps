package com.nearsoft.ccuevas.kedditbysteps.commons

/**
 * Created by ccuevas on 11/22/17.
 */
interface BaseContract {

    interface BasePresenter {

        fun subscribe()

        fun unSubscribe()

    }

    interface BaseView<T> {

    }

}