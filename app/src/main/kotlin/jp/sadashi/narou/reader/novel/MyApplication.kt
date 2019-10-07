package jp.sadashi.narou.reader.novel

import jp.sadashi.narou.reader.novel.core.di.Component
import jp.sadashi.narou.reader.novel.core.di.DIApplication
import jp.sadashi.narou.reader.novel.core.di.Injector
import jp.sadashi.narou.reader.novel.ui.search.NovelInjector
import kotlin.reflect.KClass

class MyApplication : DIApplication() {

    private val componentMap = HashMap<KClass<*>, Component>()
    private val injectorMap = HashMap<KClass<*>, Injector>()

    override fun onCreate() {
        super.onCreate()
        componentMap[NovelComponent::class] = DaggerNovelComponent.builder()
            .novelModule(NovelModule(this))
            .build()

        injectorMap[NovelInjector::class] = NovelInjectorImpl()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Component> getComponent(componentClass: KClass<T>): T =
        componentMap[componentClass] as T

    @Suppress("UNCHECKED_CAST")
    override fun <T : Injector> getInjector(injectorClass: KClass<T>): T =
        injectorMap[injectorClass] as T
}