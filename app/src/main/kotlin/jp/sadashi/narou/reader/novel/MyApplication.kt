package jp.sadashi.narou.reader.novel

import jp.sadashi.narou.reader.novel.core.di.DIApplication
import jp.sadashi.narou.reader.novel.core.di.Injector
import jp.sadashi.narou.reader.novel.ui.search.NovelInjector
import kotlin.reflect.KClass

class MyApplication : DIApplication() {

    private val injectorMap = HashMap<KClass<*>, Injector>()

    override fun onCreate() {
        super.onCreate()
        injectorMap[NovelInjector::class] = NovelInjectorImpl(
            DaggerNovelComponent.builder()
                .novelModule(NovelModule(this))
                .build()
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Injector> getInjector(injectorClass: KClass<T>): T =
        injectorMap[injectorClass] as T
}