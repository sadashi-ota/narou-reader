package jp.sadashi.narou.reader.novel.domain.dto

import jp.sadashi.narou.reader.novel.domain.NovelCode
import java.util.Date

data class NovelSummary(
    val novelCode: NovelCode,
    val title: String,
    val writer: String,
    val story: String,
    val totalRating: Int,
    val reviewCount: Int,
    val bookmarkCount: Int,
    val novelUpdatedAt: Date
)