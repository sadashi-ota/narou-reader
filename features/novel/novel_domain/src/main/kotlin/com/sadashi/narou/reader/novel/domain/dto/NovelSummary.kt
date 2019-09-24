package com.sadashi.narou.reader.novel.domain.dto

import com.sadashi.narou.reader.novel.domain.NCode
import java.util.Date

data class NovelSummary(
    val ncode: NCode,
    val title: String,
    val writer: String,
    val story: String,
    val totalRating: Int,
    val reviewCount: Int,
    val bookmarkCount: Int,
    val novelUpdatedAt: Date
)