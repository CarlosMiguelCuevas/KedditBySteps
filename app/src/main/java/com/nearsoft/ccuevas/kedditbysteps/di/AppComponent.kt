package com.nearsoft.ccuevas.kedditbysteps.di

import android.app.Application
import com.nearsoft.ccuevas.kedditbysteps.KedditApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        BindingActivityModule::class,
        AppModule::class,
        ApiModule::class,
        DataSourceModule::class,
        NetworkModule::class))
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: KedditApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }

}
