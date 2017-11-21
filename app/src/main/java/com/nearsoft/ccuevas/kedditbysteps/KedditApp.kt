package com.nearsoft.ccuevas.kedditbysteps

import android.app.Application
import com.nearsoft.ccuevas.kedditbysteps.di.AppComponent
import com.nearsoft.ccuevas.kedditbysteps.di.DaggerAppComponent

/**
 * Created by ccuevas on 11/20/17.
 */
class KedditApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
    }

}