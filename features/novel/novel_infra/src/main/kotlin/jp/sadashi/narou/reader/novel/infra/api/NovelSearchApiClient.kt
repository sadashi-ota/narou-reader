package jp.sadashi.narou.reader.novel.infra.api

import jp.sadashi.narou.reader.novel.infra.api.response.NovelSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

@Suppress("SpellCheckingInspection")
interface NovelSearchApiClient {

    @GET("novelapi/api")
    fun searchNovel(
        @Query("out") out: String = "json",
        @Query("word") word: String,
        @Query("lim") limit: Int = 100,
        @Query("st") start: Int = 1,
        @Query("order") order: String = "hyoka"
    ): Single<List<NovelSearchResponse>>
}