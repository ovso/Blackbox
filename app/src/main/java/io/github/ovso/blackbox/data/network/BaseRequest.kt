package io.github.ovso.blackbox.data.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseRequest<T> {
  val api: T
    get() = createRetrofit().create(apiClass)

  protected abstract val apiClass: Class<T>

  protected abstract val baseUrl: String

  protected abstract fun createHeaders(): Headers

  private fun createRetrofit(): Retrofit {

    return Builder().baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .client(createClient())
      .build()
  }

  private fun createClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
    httpClient.connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
    httpClient.addInterceptor { chain ->
      val original = chain.request()
      val requestBuilder = original.newBuilder()
        .header("Content-Type", "plain/text")
        .headers(this@BaseRequest.createHeaders())
      val request = requestBuilder.build()
      chain.proceed(request)
    }
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    httpClient.addInterceptor(interceptor)
    return httpClient.build()
  }

  companion object {
    const val TIMEOUT_SECONDS = 1000 * 60
  }

}
