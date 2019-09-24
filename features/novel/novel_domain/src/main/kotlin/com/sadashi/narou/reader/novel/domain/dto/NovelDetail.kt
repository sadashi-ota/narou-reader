package com.sadashi.narou.reader.novel.domain.dto

import com.sadashi.narou.reader.novel.domain.NCode

data class NovelDetail(
    val ncode: NCode,
    val title: String,
    val subtitle: String,
    val page: Int,
    val maxPage: Int,
    val body: List<String>
)