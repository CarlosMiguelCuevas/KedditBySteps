package com.nearsoft.ccuevas.kedditbysteps

import com.nearsoft.ccuevas.kedditbysteps.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 * Created by ccuevas on 11/20/17.
 */
class KedditApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent: AppComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}