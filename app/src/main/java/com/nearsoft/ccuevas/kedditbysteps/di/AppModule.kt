package com.nearsoft.ccuevas.kedditbysteps.di

import android.app.Application
import android.content.Context
import com.nearsoft.ccuevas.kedditbysteps.KedditApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ccuevas on 11/20/17.
 */
@Module
class AppModule() {

    @Provides
    @Singleton
    fun provideContext(app: KedditApp): Context = app

    @Provides
    @Singleton
    fun provideApplication(app: KedditApp): Application = app

}