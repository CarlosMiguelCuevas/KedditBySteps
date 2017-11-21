package com.nearsoft.ccuevas.kedditbysteps.di

import android.app.Application
import com.nearsoft.ccuevas.kedditbysteps.KedditApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class, BuildersModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: KedditApp)

}
