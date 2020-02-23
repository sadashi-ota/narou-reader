package jp.sadashi.narou.reader.novel.infra.domain

import jp.sadashi.narou.reader.novel.domain.NovelCode
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import jp.sadashi.narou.reader.novel.infra.api.response.NovelSearchResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal object NovelSummaryConverter {

    fun convertToDomainModelForList(responses: List<NovelSearchResponse>): List<NovelSummary> {
        return responses
            .filter { it.allcount == null }
            .map { convertToDomainModel(it) }
    }

    fun convertToDomainModel(response: NovelSearchResponse): NovelSummary {
        return NovelSummary(
            novelCode = NovelCode(response.ncode ?: throw IllegalArgumentException("novelCode is null")),
            title = response.title ?: throw IllegalArgumentException("title is null"),
            writer = response.writer ?: throw IllegalArgumentException("writer is null"),
            story = response.story ?: throw IllegalArgumentException("story is null"),
            totalRating = response.all_point ?: throw IllegalArgumentException("all_point is null"),
            reviewCount = response.review_cnt
                ?: throw IllegalArgumentException("review_cnt is null"),
            bookmarkCount = response.fav_novel_cnt
                ?: throw IllegalArgumentException("fav_novel_cnt is null"),
            novelUpdatedAt = convertData(
                response.novelupdated_at
                    ?: throw IllegalArgumentException("novelupdated_at is null")
            ),
            isBookmark = false
        )
    }

    private fun convertData(data: String): Date {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return df.parse(data)
    }
}
