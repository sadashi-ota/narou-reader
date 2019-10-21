package jp.sadashi.narou.reader.novel.infra.api

import jp.sadashi.narou.reader.novel.infra.api.response.NovelDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NovelDetailApiClient {
    @GET("{novelCode}/{page}")
    fun getNovelDetail(
        @Path("novelCode") ncode: String,
        @Path("page") page: Int
    ): Single<NovelDetailResponse>
}