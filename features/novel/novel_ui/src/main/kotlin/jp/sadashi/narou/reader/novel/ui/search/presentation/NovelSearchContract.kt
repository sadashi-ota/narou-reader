package jp.sadashi.narou.reader.novel.ui.search.presentation

import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary

interface NovelSearchContract {
    interface Presentation {
        fun setUp(view: View, viewModel: NovelSearchResultViewModel)
        fun search(word: String)
        fun loadNextPage()
        fun isAllLoaded(): Boolean
        fun loadedItemCount(): Int
        val selectNovel: (NovelSummary) -> Unit
    }

    interface View {
        fun showList(dtoList: List<NovelSummary>)
        fun clearList()
        fun showProgress()
        fun dismissProgress()
        fun showErrorDialog(throwable: Throwable)
    }
}