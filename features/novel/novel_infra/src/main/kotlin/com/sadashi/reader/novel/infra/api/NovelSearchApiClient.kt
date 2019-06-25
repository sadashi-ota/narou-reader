package com.sadashi.reader.novel.infra.api

import com.sadashi.reader.novel.infra.api.response.NovelSearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NovelSearchApiClient {

    @GET("novelapi/api")
    fun searchNovel(
        @Query("word") word: String
    ): Single<List<NovelSearchResult>>
}