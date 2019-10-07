package jp.sadashi.narou.reader.novel.ui

import jp.sadashi.narou.reader.novel.ui.search.presentation.NovelSearchContract
import jp.sadashi.narou.reader.novel.ui.search.presentation.NovelSearchPresenter
import dagger.Module
import dagger.Provides

@Module
class NovelUiModule {
    @Provides
    fun provideNovelSearchContractPresentation(presenter: NovelSearchPresenter): NovelSearchContract.Presentation {
        return presenter
    }
}
