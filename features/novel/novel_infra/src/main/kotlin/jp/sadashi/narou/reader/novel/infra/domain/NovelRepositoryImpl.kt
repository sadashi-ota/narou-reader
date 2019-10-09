package jp.sadashi.narou.reader.novel.infra.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import jp.sadashi.narou.reader.novel.domain.NCode
import jp.sadashi.narou.reader.novel.domain.NovelRepository
import jp.sadashi.narou.reader.novel.domain.dto.NovelDetail
import jp.sadashi.narou.reader.novel.domain.dto.NovelSearchResult
import jp.sadashi.narou.reader.novel.infra.api.NovelDetailApiClient
import jp.sadashi.narou.reader.novel.infra.api.NovelSearchApiClient
import javax.inject.Inject
import javax.inject.Named

class NovelRepositoryImpl @Inject constructor(
    private val searchApiClient: NovelSearchApiClient,
    private val detailApiClient: NovelDetailApiClient,
    @Named("io") private val ioScheduler: Scheduler
) : NovelRepository {
    override fun searchNovel(word: String, page: Int, limit: Int): Single<NovelSearchResult> {
        return searchApiClient.searchNovel(word = word, start = 1 + (page - 1) * limit, limit = limit)
            .map { NovelSearchResultConverter.convertToDomainModel(it) }
            .subscribeOn(ioScheduler)
    }

    override fun getNovelDetail(ncode: NCode, page: Int): Single<NovelDetail> {
        return detailApiClient.getNovelDetail(ncode = ncode.value, page = page)
            .map { NovelDetailConverter.convertToDomainModel(it) }
            .subscribeOn(ioScheduler)
    }
}