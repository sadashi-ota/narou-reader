package jp.sadashi.narou.reader.novel.infra.domain

import jp.sadashi.narou.reader.novel.domain.NCode
import jp.sadashi.narou.reader.novel.domain.NovelRepository
import jp.sadashi.narou.reader.novel.domain.dto.NovelDetail
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import jp.sadashi.narou.reader.novel.infra.api.NovelDetailApiClient
import jp.sadashi.narou.reader.novel.infra.api.NovelSearchApiClient
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class NovelRepositoryImpl @Inject constructor(
    private val searchApiClient: NovelSearchApiClient,
    private val detailApiClient: NovelDetailApiClient,
    @Named("io") private val ioScheduler: Scheduler
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