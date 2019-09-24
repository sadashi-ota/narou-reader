package com.sadashi.narou.reader.novel.ui

import com.sadashi.narou.reader.novel.ui.search.presentation.NovelSearchContract
import com.sadashi.narou.reader.novel.ui.search.presentation.NovelSearchPresenter
import dagger.Module
import dagger.Provides

@Module
internal class NovelUiModule {
    @Provides
    fun provideNovelSearchContractPresentation(presenter: NovelSearchPresenter): NovelSearchContract.Presentation {
        return presenter
    }
}
