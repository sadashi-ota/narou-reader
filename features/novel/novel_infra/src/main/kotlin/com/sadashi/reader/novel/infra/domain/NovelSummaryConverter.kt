package com.sadashi.reader.novel.infra.domain

import com.sadashi.reader.novel.domain.NCode
import com.sadashi.reader.novel.domain.dto.NovelSummary
import com.sadashi.reader.novel.infra.api.response.NovelSearchResult
import java.text.SimpleDateFormat
import java.util.Locale

object NovelSummaryConverter {

    fun convertToDomainModelForList(results: List<NovelSearchResult>): List<NovelSummary> {
        return results.filter { it.allcount == null }.map { convertToDomainModel(it) }
    }

    private fun convertToDomainModel(result: NovelSearchResult): NovelSummary {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return NovelSummary(
            ncode = NCode(result.ncode ?: throw IllegalArgumentException("ncode is null")),
            title = result.title ?: throw IllegalArgumentException("title is null"),
            writer = result.writer ?: throw IllegalArgumentException("writer is null"),
            story = result.story ?: throw IllegalArgumentException("story is null"),
            totalRating = result.all_point ?: throw IllegalArgumentException("all_point is null"),
            reviewCount = result.review_cnt ?: throw IllegalArgumentException("review_cnt is null"),
            bookmarkCount = result.fav_novel_cnt ?: throw IllegalArgumentException("fav_novel_cnt is null"),
            novelUpdatedAt = df.parse(result.novelupdated_at)
        )
    }
}
//2019-06-25 03:19:54