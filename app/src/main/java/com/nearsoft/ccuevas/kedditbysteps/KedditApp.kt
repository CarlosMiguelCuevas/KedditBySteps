package com.nearsoft.ccuevas.kedditbysteps

import android.app.Activity
import android.app.Application
import com.nearsoft.ccuevas.kedditbysteps.di.AppComponent
import com.nearsoft.ccuevas.kedditbysteps.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


/**
 * Created by ccuevas on 11/20/17.
 */
class KedditApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()

        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector;
    }
}