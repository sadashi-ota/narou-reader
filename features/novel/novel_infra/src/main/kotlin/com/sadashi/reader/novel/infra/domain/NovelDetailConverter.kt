package com.sadashi.reader.novel.infra.domain

import com.sadashi.reader.novel.domain.NCode
import com.sadashi.reader.novel.domain.dto.NovelDetail
import com.sadashi.reader.novel.infra.api.response.NovelDetailResponse

object NovelDetailConverter {
    fun convertToDomainModel(response: NovelDetailResponse): NovelDetail {
        return NovelDetail(
            ncode = NCode(response.title ?: ""),
            title = response.title ?: "",
            subtitle = response.subtitle ?: "",
            page = 1,
            maxPage = 100,
            body = response.body ?: emptyList()
        )
    }
}