package jp.sadashi.narou.reader.novel.ui.search.presentation

import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary

interface NovelSearchContract {
    interface Presentation {
        fun setUp(
            view: View,
            viewModel: NovelSearchResultViewModel,
            transition: NovelSearchTransition
        )

        fun isExistLoadData(): Boolean
        fun search(word: String)
        fun refresh()
        fun loadNextPage()
        fun isAllLoaded(): Boolean
        fun loadedItemCount(): Int
        fun getWord(): String?

        val selectNovel: (NovelSummary) -> Unit
        val bookmarkNovel: (NovelSummary) -> Unit
    }

    interface View {
        fun showList(dtoList: List<NovelSummary>)
        fun update(summary: NovelSummary)
        fun clearList()
        fun showProgress()
        fun dismissProgress()
        fun showErrorDialog(throwable: Throwable)
    }
}