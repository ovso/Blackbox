package io.github.ovso.blackbox.data.network;

import android.text.TextUtils;
import io.github.ovso.blackbox.Security;
import io.github.ovso.blackbox.data.KeyName;
import io.github.ovso.blackbox.data.network.model.Search;
import io.reactivex.Single;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.Headers;

public class SearchRequest extends BaseRequest<SearchService> {

  @Inject public SearchRequest() {

  }

  @Override protected Class<SearchService> getApiClass() {
    return SearchService.class;
  }

  @Override protected Headers createHeaders() {

    return new Headers.Builder().build();
  }

  @Override protected String getBaseUrl() {
    return ApiEndPoint.SEARCH.getUrl();
  }

  public Single<Search> getResult(String q, String pageToken) {
    return getApi().getResult(createQueryMap(q, pageToken));
  }

  private Map<String, Object> createQueryMap(String q, String pageToken) {
    Map<String, Object> queryMap = new HashMap<>();
    queryMap.put(KeyName.Q.get(), q);
    queryMap.put(KeyName.MAX_RESULTS.get(), 50);
    queryMap.put(KeyName.ORDER.get(), "viewCount");
    queryMap.put(KeyName.TYPE.get(), "video");
    queryMap.put(KeyName.VIDEO_SYNDICATED.get(), "any");
    queryMap.put(KeyName.KEY.get(), Security.KEY.getValue());
    queryMap.put(KeyName.PART.get(), "snippet");
    if (!TextUtils.isEmpty(pageToken)) {
      queryMap.put(KeyName.PAGE_TOKEN.get(), pageToken);
    }
    return queryMap;
  }
}