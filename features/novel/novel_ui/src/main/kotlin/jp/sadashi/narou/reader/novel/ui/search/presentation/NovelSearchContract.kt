package jp.sadashi.narou.reader.novel.ui.search.presentation

interface NovelSearchContract {
    interface Presentation {
        fun setUp(view: View)
        fun search()
    }

    interface View {
        fun showProgress()
        fun dismissProgress()
        fun showErrorDialog(throwable: Throwable)
    }
}