package com.nearsoft.ccuevas.kedditbysteps.displaynews.di

import com.nearsoft.ccuevas.kedditbysteps.data.source.RepositoryDataSource
import com.nearsoft.ccuevas.kedditbysteps.di.scopes.FragmentScope
import com.nearsoft.ccuevas.kedditbysteps.displaynews.DisplayNewsContract
import com.nearsoft.ccuevas.kedditbysteps.displaynews.NewsFragment
import com.nearsoft.ccuevas.kedditbysteps.displaynews.NewsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by ccuevas on 11/20/17.
 */
@Module
class NewsListModule {

    @Provides
    @FragmentScope
    @Named("NewListPresenter")
    fun providePresenter(@Named("RepositoryDataSource") dataSource: RepositoryDataSource, view: NewsFragment): DisplayNewsContract.Presenter {
        return NewsPresenter(dataSource, view)
    }

}