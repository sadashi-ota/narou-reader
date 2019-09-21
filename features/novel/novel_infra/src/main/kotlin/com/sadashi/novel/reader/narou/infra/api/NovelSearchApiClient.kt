package com.sadashi.novel.reader.narou.infra.api

import com.sadashi.novel.reader.narou.infra.api.response.NovelSearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NovelSearchApiClient {

    @GET("novelapi/api")
    fun searchNovel(
        @Query("out") out: String = "json",
        @Query("word") word: String
    ): Single<List<NovelSearchResult>>
}