package com.sadashi.reader.novel.infra.domain

import com.sadashi.reader.novel.domain.NCode
import com.sadashi.reader.novel.domain.dto.NovelDetail
import com.sadashi.reader.novel.infra.api.response.NovelDetailResponse

object NovelDetailConverter {
    fun convertToDomainModel(response: NovelDetailResponse): NovelDetail {
        val ncode = response.ncode ?: throw IllegalStateException("ncode is null.")
        val pageStr = response.novelNo ?: throw IllegalStateException("novel_no is null.")
        val (page, maxPage) = extractPage(pageStr)

        return NovelDetail(
            ncode = NCode(ncode.replace("/", "")),
            title = response.title ?: throw IllegalStateException("title is null."),
            subtitle = response.subtitle ?: throw IllegalStateException("subtitle is null."),
            page = page,
            maxPage = maxPage,
            body = response.body ?: emptyList()
        )
    }

    private fun extractPage(novelNo: String): Pair<Int, Int> {
        val pages = novelNo.split("/")
        (pages.size == 2) || throw IllegalStateException("page parse error.")

        return Pair(pages[0].toInt(), pages[1].toInt())
    }
}