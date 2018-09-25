package io.github.ovso.blackbox.data.network;

import io.github.ovso.blackbox.data.network.model.Search;
import io.reactivex.Single;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface SearchService {
  @GET("youtube/v3/search")
  Single<Search> getResult(@QueryMap Map<String, Object> queryMap);
}
