package jp.sadashi.narou.reader.novel.usecase

import io.reactivex.Single
import jp.sadashi.narou.reader.novel.domain.dto.NovelSearchResult

interface SearchNovelUseCase {
    fun execute(word: String, page: Int = 1): Single<NovelSearchResult>
}