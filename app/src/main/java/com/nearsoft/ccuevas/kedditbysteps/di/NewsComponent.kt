package com.nearsoft.ccuevas.kedditbysteps.di

import com.nearsoft.ccuevas.kedditbysteps.NewsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ccuevas on 11/20/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, NewsModule::class))
interface NewsComponent {

    fun inject(fragment: NewsFragment)

}