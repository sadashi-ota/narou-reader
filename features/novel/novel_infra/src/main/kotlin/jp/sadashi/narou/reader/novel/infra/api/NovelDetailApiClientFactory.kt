package jp.sadashi.narou.reader.novel.infra.api

import jp.sadashi.narou.reader.novel.infra.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object NovelDetailApiClientFactory {

    fun create(): NovelDetailApiClient {
        return provideRetrofit().create(NovelDetailApiClient::class.java)
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(jp.sadashi.narou.reader.novel.infra.BuildConfig.HTML_DOMAIN)
            .client(createClient())
            .addConverterFactory(JspoonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (jp.sadashi.narou.reader.novel.infra.BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        okHttpClientBuilder.addInterceptor(Interceptor { chain ->
            val original = chain.request()

            //header
            val request = original.newBuilder()
                .header("Accept", "text/html")
                .method(original.method, original.body)
                .build()

            return@Interceptor chain.proceed(request)
        })
        return okHttpClientBuilder.build()
    }
}