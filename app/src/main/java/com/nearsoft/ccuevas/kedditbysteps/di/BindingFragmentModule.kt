package com.nearsoft.ccuevas.kedditbysteps.di

import com.nearsoft.ccuevas.kedditbysteps.di.scopes.FragmentScope
import com.nearsoft.ccuevas.kedditbysteps.displaynews.NewsActivity
import com.nearsoft.ccuevas.kedditbysteps.displaynews.NewsFragment
import com.nearsoft.ccuevas.kedditbysteps.displaynews.di.NewsListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ccuevas on 11/20/17.
 */

@Module
abstract class BindingFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(NewsListModule::class))
    abstract fun bindNewsFragment(): NewsFragment
}