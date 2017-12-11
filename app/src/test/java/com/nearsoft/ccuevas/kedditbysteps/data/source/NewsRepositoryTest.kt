package com.nearsoft.ccuevas.kedditbysteps.data.source

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNewsItem
import com.nearsoft.ccuevas.kedditbysteps.data.source.local.cache.CachedContract
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by ccuevas on 12/8/17.
 */
class NewsRepositoryTest {

    @Mock
    lateinit var mApi: BaseDataSource
    @Mock
    lateinit var mCache: CachedContract

    lateinit var mNewsRepository: RepositoryDataSource
    var testSubscriber: TestObserver<RedditNews> = TestObserver<RedditNews>()
    lateinit var mFreshObject: RedditNews
    lateinit var mCachedObejct: RedditNews
    val after = ""
    val limit = "10"

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    @Before
    fun setUp() {

        //inflate mocks
        MockitoAnnotations.initMocks(this)

        mNewsRepository = NewsRepository(mCache, mApi)

        //returning data
        val mCachedNewsList = ArrayList<RedditNewsItem>()
        val mFreshNewsList = ArrayList<RedditNewsItem>()
        mCachedObejct = RedditNews(after, limit, mCachedNewsList)
        mFreshObject = RedditNews(after, limit, mFreshNewsList)

        mCachedNewsList.add(RedditNewsItem("authorFake1", "fakeTitle", 100, 10101001, "none", "none"))
        mCachedNewsList.add(RedditNewsItem("authorFake2", "fakeTitle", 100, 10101001, "none", "none"))

        mFreshNewsList.add(RedditNewsItem("authorFresh1", "fakeTitle", 100, 10101001, "none", "none"))
        mFreshNewsList.add(RedditNewsItem("authorFresh2", "fakeTitle", 100, 10101001, "none", "none"))
        mFreshNewsList.add(RedditNewsItem("authorFresh3", "fakeTitle", 100, 10101001, "none", "none"))


        Mockito.`when`(mApi.getNews()).thenReturn(Observable.just(mFreshObject))
        Mockito.`when`(mCache.getNews()).thenReturn(mCachedObejct)

    }

    @Test
    fun getNews_freshData_noErrorInTheStream() {

        //execute
        mNewsRepository.setRefreshNews()
        mNewsRepository.getNews(after, limit).subscribe(testSubscriber)

        //assertion
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        testSubscriber.assertComplete()

    }

    @Test
    fun getNews_freshData_justCallApiMethod() {

        //execute
        mNewsRepository.setRefreshNews()
        mNewsRepository.getNews(after, limit).subscribe(testSubscriber)

        //assertion
        verify(mApi).getNews(Matchers.anyString(), Matchers.anyString())
        verify(mCache).saveNews(mFreshObject)

    }

    @Test
    fun getNews_CachedData_noErrorInTheStream() {

        //execute
        mNewsRepository.getNews(after, limit).subscribe(testSubscriber)

        //assertion
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        testSubscriber.assertComplete()

    }

    @Test
    fun getNews_CachedData_getCorrectData() {

        //execute
        mNewsRepository.getNews(after, limit).subscribe(testSubscriber)

        //assertion
        testSubscriber.assertValue({gottenValue -> gottenValue.equals(mCachedObejct)})

    }

    @Test
    fun getNews_CachedData_justCallCachedGetMethod() {

        //execute
        mNewsRepository.getNews(after, limit).subscribe(testSubscriber)

        //assertion
        verify(mApi, never()).getNews(Matchers.anyString(), Matchers.anyString())
        verify(mCache, never()).saveNews(any())
        verify(mCache).getNews()

    }

}