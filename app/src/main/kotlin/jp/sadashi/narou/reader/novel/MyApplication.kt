package jp.sadashi.narou.reader.novel

import jp.sadashi.narou.reader.novel.core.di.Component
import jp.sadashi.narou.reader.novel.core.di.DIApplication
import kotlin.reflect.KClass

class MyApplication : jp.sadashi.narou.reader.novel.core.di.DIApplication() {

    private val componentMap = HashMap<KClass<*>, jp.sadashi.narou.reader.novel.core.di.Component>()

    override fun onCreate() {
        super.onCreate()
        componentMap[jp.sadashi.narou.reader.novel.NovelComponent::class] = jp.sadashi.narou.reader.novel.DaggerNovelComponent.builder()
            .novelModule(jp.sadashi.narou.reader.novel.NovelModule(this))
            .build()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : jp.sadashi.narou.reader.novel.core.di.Component> getComponent(componentClass: KClass<T>): T =
        componentMap[componentClass] as T
}