package com.sadashi.narou.reader.novel.infra.api

import com.sadashi.narou.reader.novel.infra.api.response.NovelDetailResponse
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