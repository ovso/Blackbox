package io.github.ovso.blackbox.data.network

import android.text.TextUtils
import io.github.ovso.blackbox.Security
import io.github.ovso.blackbox.data.KeyName
import io.github.ovso.blackbox.data.network.model.Search
import io.reactivex.Single
import java.util.HashMap
import javax.inject.Inject
import okhttp3.Headers

class SearchRequest @Inject constructor() : BaseRequest<SearchService>() {
  override val apiClass: Class<SearchService>
    get() = SearchService::class.java
  override val baseUrl: String
    get() = ApiEndPoint.SEARCH.url

  override fun createHeaders(): Headers {

    return Headers.Builder()
        .build()
  }

  fun getResult(
    q: String,
    pageToken: String?
  ): Single<Search> {
    return api.getResult(createQueryMap(q, pageToken))
  }

  private fun createQueryMap(
    q: String,
    pageToken: String?
  ): Map<String, Any> {
    val queryMap = HashMap<String, Any>()
    queryMap[KeyName.Q.get()] = q
    queryMap[KeyName.MAX_RESULTS.get()] = 50
    queryMap[KeyName.ORDER.get()] = "date"
    queryMap[KeyName.TYPE.get()] = "video"
    queryMap[KeyName.VIDEO_SYNDICATED.get()] = "true"
    queryMap[KeyName.KEY.get()] = Security.KEY.value
    queryMap[KeyName.PART.get()] = "snippet"
    if (!TextUtils.isEmpty(pageToken)) queryMap[KeyName.PAGE_TOKEN.get()] = pageToken!!
    return queryMap
  }
}