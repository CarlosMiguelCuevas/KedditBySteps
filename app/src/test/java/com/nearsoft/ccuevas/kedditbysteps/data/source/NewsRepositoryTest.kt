package com.nearsoft.ccuevas.kedditbysteps.data.source

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by ccuevas on 12/8/17.
 */
class NewsRepositoryTest {

    @Mock
    lateinit var mApi: BaseDataSource

    lateinit var mNewsRepository : RepositoryDataSource
    var testSubscriber: TestObserver<RedditNews> = TestObserver<RedditNews>()

    @Before
    fun setUp() {

        //inflate mocks
        MockitoAnnotations.initMocks(this)

        mNewsRepository = NewsRepository(mApi)
    }

    @Test
    fun getNews_CachedData() {

        //set up
        val after = ""
        val limit = "10"

        //execute
        mNewsRepository.getNews(after, limit).subscribe(testSubscriber)

        //assertion
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
        testSubscriber.assertComplete()
    }

//    @Test
//    fun getNews_FreshData() {
//
//        //execute
//        mRepository.getNews(after, limit)
//
//        //assertion
//        //calle api
//
//    }

}