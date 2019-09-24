package com.sadashi.narou.reader.novel

import com.sadashi.narou.reader.novel.core.di.Component
import com.sadashi.narou.reader.novel.ui.search.view.NovelSearchActivity
import com.sadashi.narou.reader.novel.ui.search.view.NovelSearchFragment
import javax.inject.Singleton

@Singleton
@dagger.Component(
    modules = [
        NovelModule::class
    ]
)
interface NovelComponent : Component {
    fun inject(activity: NovelSearchActivity)
    fun inject(fragment: NovelSearchFragment)
}
