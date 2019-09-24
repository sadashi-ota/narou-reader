package com.sadashi.narou.reader.novel.infra.domain

import com.sadashi.narou.reader.novel.domain.NCode
import com.sadashi.narou.reader.novel.domain.dto.NovelSummary
import com.sadashi.narou.reader.novel.infra.api.response.NovelSearchResult
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object NovelSummaryConverter {

    fun convertToDomainModelForList(results: List<NovelSearchResult>): List<NovelSummary> {
        return results.map { convertToDomainModel(it) }
    }

    fun convertToDomainModel(result: NovelSearchResult): NovelSummary {
        return NovelSummary(
            ncode = NCode(result.ncode),
            title = result.title,
            writer = result.writer,
            story = result.story,
            totalRating = result.all_point,
            reviewCount = result.review_cnt,
            bookmarkCount = result.fav_novel_cnt,
            novelUpdatedAt = convertData(result.novelupdated_at)
        )
    }

    private fun convertData(data: String): Date {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return df.parse(data)
    }
}
