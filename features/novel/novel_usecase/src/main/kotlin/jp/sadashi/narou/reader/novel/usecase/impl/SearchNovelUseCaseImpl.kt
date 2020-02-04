package jp.sadashi.narou.reader.novel.usecase.impl

import jp.sadashi.narou.reader.novel.domain.NovelRepository
import jp.sadashi.narou.reader.novel.usecase.SearchNovelUseCase
import javax.inject.Inject

class SearchNovelUseCaseImpl @Inject constructor(
    private val novelRepository: NovelRepository
) : SearchNovelUseCase {
    override fun execute(word: String, page: Int) = novelRepository.searchNovel(word, page)
}