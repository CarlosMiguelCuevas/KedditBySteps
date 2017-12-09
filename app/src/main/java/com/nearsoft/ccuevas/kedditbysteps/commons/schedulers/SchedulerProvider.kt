package com.nearsoft.ccuevas.kedditbysteps.commons.schedulers

import io.reactivex.Scheduler

/**
 * Created by ccuevas on 12/8/17.
 */
interface SchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler

}