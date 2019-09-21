package com.sadashi.novel.reader.narou.domain

import com.sadashi.novel.reader.narou.domain.dto.NovelDetail
import com.sadashi.novel.reader.narou.domain.dto.NovelSummary
import io.reactivex.Single

interface NovelRepository {
    fun searchNovel(word: String): Single<List<NovelSummary>>
    fun getNovelDetail(ncode: NCode, page: Int): Single<NovelDetail>
}