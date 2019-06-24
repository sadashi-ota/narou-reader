package com.sadashi.reader.novel.infra.domain

import com.sadashi.reader.novel.domain.NCode
import com.sadashi.reader.novel.domain.NovelRepository
import com.sadashi.reader.novel.domain.dto.NovelDetail
import com.sadashi.reader.novel.domain.dto.NovelSummary
import com.sadashi.reader.novel.infra.api.NovelDetailApiClient
import com.sadashi.reader.novel.infra.api.NovelSearchApiClient
import io.reactivex.Scheduler
import io.reactivex.Single

class NovelRepositoryImpl(
    private val searchApiClient: NovelSearchApiClient,
    private val detailApiClient: NovelDetailApiClient,
    private val ioScheduler: Scheduler
) : NovelRepository {
    override fun searchNovel(word: String): Single<List<NovelSummary>> {
        return searchApiClient.searchNovel(word)
            .map { NovelSummaryConverter.convertToDomainModelForList(it) }
            .subscribeOn(ioScheduler)
    }

    override fun getNovelDetail(ncode: NCode, page: Int): Single<NovelDetail> {
        return detailApiClient.getNovelDetail(ncode = ncode.value, page = page)
            .map { NovelDetailConverter.convertToDomainModel(it) }
            .subscribeOn(ioScheduler)
    }
}