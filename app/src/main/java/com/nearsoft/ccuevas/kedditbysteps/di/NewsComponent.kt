package com.nearsoft.ccuevas.kedditbysteps.di

import com.nearsoft.ccuevas.kedditbysteps.NewsFragment
import dagger.Subcomponent

/**
 * Created by ccuevas on 11/20/17.
 */
@FragmentScope
@Subcomponent(modules = arrayOf(NewsModule::class))
interface NewsComponent {

    fun inject(fragment: NewsFragment)

}