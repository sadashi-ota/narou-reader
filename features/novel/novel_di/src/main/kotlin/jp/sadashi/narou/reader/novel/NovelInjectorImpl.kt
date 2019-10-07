package jp.sadashi.narou.reader.novel

import android.content.Context
import jp.sadashi.narou.reader.novel.core.di.DIApplication
import jp.sadashi.narou.reader.novel.ui.search.NovelInjector
import jp.sadashi.narou.reader.novel.ui.search.view.NovelSearchFragment

class NovelInjectorImpl : NovelInjector {

    override fun execute(context: Context, fragment: NovelSearchFragment) {
        DIApplication.get(context).getComponent(NovelComponent::class).inject(fragment)
    }
}