package jp.sadashi.narou.reader.novel.ui.search

import android.content.Context
import jp.sadashi.narou.reader.novel.core.di.Injector
import jp.sadashi.narou.reader.novel.ui.search.view.NovelSearchFragment

interface NovelInjector : Injector {
    fun execute(context: Context, fragment: NovelSearchFragment)
}