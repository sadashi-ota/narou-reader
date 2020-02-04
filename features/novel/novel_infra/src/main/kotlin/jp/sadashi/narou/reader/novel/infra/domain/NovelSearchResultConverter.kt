package jp.sadashi.narou.reader.novel.infra.domain

import jp.sadashi.narou.reader.novel.domain.dto.NovelSearchResult
import jp.sadashi.narou.reader.novel.infra.api.response.NovelSearchResponse

internal object NovelSearchResultConverter {

    fun convertToDomainModel(responses: List<NovelSearchResponse>): NovelSearchResult {
        val pair = responses.partition { it.allcount != null }

        val allCount = pair.first[0].allcount ?: throw IllegalArgumentException("allCount is nothing.")

        return NovelSearchResult(
            allCount = allCount,
            novelList = NovelSummaryConverter.convertToDomainModelForList(pair.second)
        )
    }
}