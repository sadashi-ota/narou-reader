package jp.sadashi.narou.reader.novel.domain

import io.reactivex.Single
import jp.sadashi.narou.reader.novel.domain.dto.NovelDetail
import jp.sadashi.narou.reader.novel.domain.dto.NovelSearchResult

interface NovelRepository {
    companion object {
        const val LIMIT_OF_SEARCH_COUNT = 100
    }

    fun searchNovel(
        word: String,
        page: Int = 1,
        limit: Int = LIMIT_OF_SEARCH_COUNT
    ): Single<NovelSearchResult>

    fun getNovelDetail(ncode: NovelCode, page: Int): Single<NovelDetail>
}