package com.sadashi.novel.reader.narou.infra.domain

import com.sadashi.novel.reader.narou.domain.NCode
import com.sadashi.novel.reader.narou.domain.NovelRepository
import com.sadashi.novel.reader.narou.domain.dto.NovelDetail
import com.sadashi.novel.reader.narou.domain.dto.NovelSummary
import com.sadashi.novel.reader.narou.infra.api.NovelDetailApiClient
import com.sadashi.novel.reader.narou.infra.api.NovelSearchApiClient
import io.reactivex.Scheduler
import io.reactivex.Single

class NovelRepositoryImpl(
    private val searchApiClient: NovelSearchApiClient,
    private val detailApiClient: NovelDetailApiClient,
    private val ioScheduler: Scheduler
) : NovelRepository {
    override fun searchNovel(word: String): Single<List<NovelSummary>> {
        return searchApiClient.searchNovel(word = word)
            .map { NovelSummaryConverter.convertToDomainModelForList(it) }
            .subscribeOn(ioScheduler)
    }

    override fun getNovelDetail(ncode: NCode, page: Int): Single<NovelDetail> {
        return detailApiClient.getNovelDetail(ncode = ncode.value, page = page)
            .map { NovelDetailConverter.convertToDomainModel(it) }
            .subscribeOn(ioScheduler)
    }
}