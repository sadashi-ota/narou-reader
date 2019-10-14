package jp.sadashi.narou.reader.novel.ui.search.presentation

import androidx.lifecycle.ViewModel
import jp.sadashi.narou.reader.novel.domain.dto.NovelSearchResult
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary

class NovelSearchResultViewModel : ViewModel() {
    var searchWord: String? = null
        private set

    var loadedPageCount: Int = 0
        private set

    var allCount = 0
        private set

    var novelList: MutableList<NovelSummary> = mutableListOf()
        private set

    fun isAllLoaded(): Boolean = (novelList.size == allCount)

    fun getNextPage(): Int = (loadedPageCount + 1)

    fun clear() {
        searchWord = null
        loadedPageCount = 0
        allCount = 0
        novelList.clear()
    }

    fun add(word: String, result: NovelSearchResult) {
        searchWord = word
        novelList.addAll(result.novelList)
        allCount = result.allCount
        loadedPageCount++
    }
}