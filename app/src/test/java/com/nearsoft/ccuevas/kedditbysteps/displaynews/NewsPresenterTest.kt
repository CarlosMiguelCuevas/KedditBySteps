package com.nearsoft.ccuevas.kedditbysteps.displaynews

import com.nearsoft.ccuevas.kedditbysteps.commons.schedulers.SchedulerProvider
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNewsItem
import com.nearsoft.ccuevas.kedditbysteps.data.source.RepositoryDataSource
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.anyListOf
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


/**
 * Created by ccuevas on 12/7/17.
 */
class NewsPresenterTest {

    @Mock
    private lateinit var mView: DisplayNewsContract.View

    @Mock
    private lateinit var mNewsRepository: RepositoryDataSource

    @Mock
    private lateinit var mSchedulers: SchedulerProvider

    private lateinit var mNewsPresenter: DisplayNewsContract.Presenter

    private lateinit var mFakeCachedData: RedditNews

    @Before
    fun setUpPresenter() {
        //inflate mocks
        MockitoAnnotations.initMocks(this)

        mNewsPresenter = NewsPresenter(mNewsRepository, mView, mSchedulers)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        configureFakeData()
        Mockito.`when`(mSchedulers.ui()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(mSchedulers.io()).thenReturn(Schedulers.trampoline())
    }

    private fun configureFakeData() {

        val fakeCachedNewslist: ArrayList<RedditNewsItem> = ArrayList()
        fakeCachedNewslist.add(RedditNewsItem("fake1", "fakeTitle", 1, 12341234, "", ""))
        fakeCachedNewslist.add(RedditNewsItem("fake2", "fakeTitle", 1, 12341234, "", ""))
        fakeCachedNewslist.add(RedditNewsItem("fake3", "fakeTitle", 1, 12341234, "", ""))
        fakeCachedNewslist.add(RedditNewsItem("fake4", "fakeTitle", 1, 12341234, "", ""))
        fakeCachedNewslist.add(RedditNewsItem("fake5", "fakeTitle", 1, 12341234, "", ""))
        fakeCachedNewslist.add(RedditNewsItem("fake6", "fakeTitle", 1, 12341234, "", ""))
        fakeCachedNewslist.add(RedditNewsItem("fake7", "fakeTitle", 1, 12341234, "", ""))
        fakeCachedNewslist.add(RedditNewsItem("fake8", "fakeTitle", 1, 12341234, "", ""))
        fakeCachedNewslist.add(RedditNewsItem("fake9", "fakeTitle", 1, 12341234, "", ""))
        fakeCachedNewslist.add(RedditNewsItem("fake10", "fakeTitle", 1, 12341234, "", ""))

        mFakeCachedData = RedditNews("10", "0", fakeCachedNewslist)
    }


    @Test
    fun requestNews_refreshingDataMethod_called() {
        //set up
        val useCashedData: Boolean = false
        Mockito.`when`(mNewsRepository.getNews()).thenReturn(Observable.just(mFakeCachedData))

        //execute
        mNewsPresenter.requestNews(useCashedData)

        //assertion
        verify(mNewsRepository).setRefreshNews()
    }

    @Test
    fun requestNews_refreshingDataMethod_notCalled() {
        //set up
        val useCashedData: Boolean = true
        Mockito.`when`(mNewsRepository.getNews()).thenReturn(Observable.just(mFakeCachedData))

        //execute
        mNewsPresenter.requestNews(useCashedData)

        //assertion
        verify(mNewsRepository, never()).setRefreshNews()
    }

    @Test
    fun requestNews_getData_success() {
        //set up
        val useCashedData: Boolean = false
        Mockito.`when`(mNewsRepository.getNews()).thenReturn(Observable.just(mFakeCachedData))

        //execute
        mNewsPresenter.requestNews(useCashedData)

        //assertion
        verify(mView).showNews(anyListOf(RedditNewsItem::class.java))
    }

    @Test
    fun requestNews_getData_error() {
        //set up
        val useCashedData: Boolean = false
        val error: Throwable = Throwable("Error, something happened")
        Mockito.`when`(mNewsRepository.getNews()).thenReturn(Observable.error(error))

        //execute
        mNewsPresenter.requestNews(useCashedData)

        //assertion
        verify(mView).showError(error)
    }

}