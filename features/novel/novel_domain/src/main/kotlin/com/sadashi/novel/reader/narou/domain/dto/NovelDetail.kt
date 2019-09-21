package com.sadashi.novel.reader.narou.domain.dto

import com.sadashi.novel.reader.narou.domain.NCode

data class NovelDetail(
    val ncode: NCode,
    val title: String,
    val subtitle: String,
    val page: Int,
    val maxPage: Int,
    val body: List<String>
)