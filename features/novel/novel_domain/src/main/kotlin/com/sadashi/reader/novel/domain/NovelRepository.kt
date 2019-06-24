package com.sadashi.reader.novel.domain

import com.sadashi.reader.novel.domain.dto.NovelDetail
import com.sadashi.reader.novel.domain.dto.NovelSummary
import io.reactivex.Single

interface NovelRepository {
    fun searchNovel(word: String): Single<List<NovelSummary>>
    fun getNovelDetail(ncode: NCode, page: Int): Single<NovelDetail>
}