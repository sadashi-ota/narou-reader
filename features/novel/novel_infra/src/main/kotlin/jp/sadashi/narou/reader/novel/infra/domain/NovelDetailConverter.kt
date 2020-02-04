package jp.sadashi.narou.reader.novel.infra.domain

import jp.sadashi.narou.reader.novel.domain.NovelCode
import jp.sadashi.narou.reader.novel.domain.dto.NovelDetail
import jp.sadashi.narou.reader.novel.infra.api.response.NovelDetailResponse
import java.lang.IllegalArgumentException

object NovelDetailConverter {
    fun convertToDomainModel(response: NovelDetailResponse): NovelDetail {
        val ncode = response.ncode ?: throw IllegalArgumentException("novelCode is null.")
        val pageStr = response.novelNo ?: throw IllegalArgumentException("novel_no is null.")
        val (page, maxPage) = extractPage(pageStr)

        return NovelDetail(
            ncode = NovelCode(ncode.replace("/", "")),
            title = response.title ?: throw IllegalArgumentException("title is null."),
            subtitle = response.subtitle ?: throw IllegalArgumentException("subtitle is null."),
            page = page,
            maxPage = maxPage,
            body = response.body ?: emptyList()
        )
    }

    private fun extractPage(novelNo: String): Pair<Int, Int> {
        val pages = novelNo.split("/")
        (pages.size == 2) || throw IllegalArgumentException("page parse error.")

        return Pair(pages[0].toInt(), pages[1].toInt())
    }
}