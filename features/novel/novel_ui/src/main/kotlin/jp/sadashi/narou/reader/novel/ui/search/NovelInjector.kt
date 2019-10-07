package jp.sadashi.narou.reader.novel.ui.search

import jp.sadashi.narou.reader.novel.core.di.Injector
import jp.sadashi.narou.reader.novel.ui.search.view.NovelSearchFragment

interface NovelInjector : Injector {
    fun inject(fragment: NovelSearchFragment)
}