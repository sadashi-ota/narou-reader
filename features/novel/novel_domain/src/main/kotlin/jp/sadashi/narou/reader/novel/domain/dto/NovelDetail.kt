package jp.sadashi.narou.reader.novel.domain.dto

import jp.sadashi.narou.reader.novel.domain.NovelCode

data class NovelDetail(
    val ncode: NovelCode,
    val title: String,
    val subtitle: String,
    val page: Int,
    val maxPage: Int,
    val body: List<String>
)