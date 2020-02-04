package jp.sadashi.narou.reader.novel.ui.search.presentation

import jp.sadashi.narou.reader.novel.domain.NovelCode

interface NovelSearchTransition {
    fun moveNovelDetail(novelCode: NovelCode)
}