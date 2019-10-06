package jp.sadashi.narou.reader.novel.infra.domain

import jp.sadashi.narou.reader.novel.domain.NCode
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import jp.sadashi.narou.reader.novel.infra.api.response.NovelSearchResult
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object NovelSummaryConverter {

    fun convertToDomainModelForList(results: List<NovelSearchResult>): List<NovelSummary> {
        return results
            .filter { it.allcount == null }
            .map { convertToDomainModel(it) }
    }

    fun convertToDomainModel(result: NovelSearchResult): NovelSummary {
        return NovelSummary(
            ncode = NCode(result.ncode ?: throw IllegalArgumentException("ncode is null")),
            title = result.title ?: throw IllegalArgumentException("title is null"),
            writer = result.writer ?: throw IllegalArgumentException("writer is null"),
            story = result.story ?: throw IllegalArgumentException("story is null"),
            totalRating = result.all_point ?: throw IllegalArgumentException("all_point is null"),
            reviewCount = result.review_cnt ?: throw IllegalArgumentException("review_cnt is null"),
            bookmarkCount = result.fav_novel_cnt
                ?: throw IllegalArgumentException("fav_novel_cnt is null"),
            novelUpdatedAt = convertData(
                result.novelupdated_at ?: throw IllegalArgumentException("novelupdated_at is null")
            )
        )
    }

    private fun convertData(data: String): Date {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return df.parse(data)
    }
}
