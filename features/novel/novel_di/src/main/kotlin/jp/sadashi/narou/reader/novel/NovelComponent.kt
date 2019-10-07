package jp.sadashi.narou.reader.novel

import jp.sadashi.narou.reader.novel.ui.search.view.NovelSearchFragment
import javax.inject.Singleton

@Singleton
@dagger.Component(
    modules = [
        NovelModule::class
    ]
)
interface NovelComponent {
    fun inject(fragment: NovelSearchFragment)
}
