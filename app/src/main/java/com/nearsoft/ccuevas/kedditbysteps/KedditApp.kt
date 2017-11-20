package com.nearsoft.ccuevas.kedditbysteps

import android.app.Application
import com.nearsoft.ccuevas.kedditbysteps.di.AppModule
import com.nearsoft.ccuevas.kedditbysteps.di.DaggerNewsComponent
import com.nearsoft.ccuevas.kedditbysteps.di.NewsComponent

/**
 * Created by ccuevas on 11/20/17.
 */
class KedditApp : Application(){

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}