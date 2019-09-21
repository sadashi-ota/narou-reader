package com.sadashi.novel.reader.narou.infra.api

import com.sadashi.novel.reader.narou.infra.api.response.NovelDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NovelDetailApiClient {
    @GET("{ncode}/{page}")
    fun getNovelDetail(
        @Path("ncode") ncode: String,
        @Path("page") page: Int
    ): Single<NovelDetailResponse>
}