package kedditbysteps.data.source.remote

import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNews
import com.nearsoft.ccuevas.kedditbysteps.data.newsmodels.RedditNewsItem
import com.nearsoft.ccuevas.kedditbysteps.data.source.BaseDataSource
import io.reactivex.Observable

/**
 * Created by ccuevas on 11/17/17.
 */
class MockedNewsRemoteDataSource() : BaseDataSource {

    override fun getNews(after: String, limit: String): Observable<RedditNews> {
        return getData(if (after == "") {0} else {after.toInt()} , limit.toInt())
    }

    private fun getData(initial: Int, limit: Int): Observable<RedditNews> {

        val mMokedList: ArrayList<RedditNewsItem> = ArrayList<RedditNewsItem>()
        val last : Int = initial + limit

        for (newsIndex in initial until last) {
            mMokedList.add(generateNews(newsIndex))
        }

        return Observable.just(RedditNews( last.toString(), (last + limit).toString(), mMokedList ))
    }

    private fun generateNews(index: Int) : RedditNewsItem {
        return RedditNewsItem("me", "fake title ${index}", 100, 1512612965, "https://www.imagely.com/wordpress-gallery-plugin/basic-thumbnail-gallery/#gallery/80e6b6750cefe261196a6944c79deae3/739", "www.notfound.com")
    }

}