package jp.sadashi.narou.reader.novel.domain.dto

data class NovelSearchResult(
    val allCount: Int,
    val novelList: List<NovelSummary>
)