package jp.sadashi.narou.reader.novel

import jp.sadashi.narou.reader.novel.ui.search.NovelInjector
import jp.sadashi.narou.reader.novel.ui.search.view.NovelSearchFragment

class NovelInjectorImpl(
    private val component: NovelComponent
) : NovelInjector {
    override fun inject(fragment: NovelSearchFragment) = component.inject(fragment)
}