package com.sadashi.narou.reader.novel

import com.sadashi.narou.reader.novel.core.di.Component
import com.sadashi.narou.reader.novel.core.di.DIApplication
import kotlin.reflect.KClass

class MyApplication : DIApplication() {

    private val componentMap = HashMap<KClass<*>, Component>()

    override fun onCreate() {
        super.onCreate()
        componentMap[NovelComponent::class] = DaggerNovelComponent.builder()
            .novelModule(NovelModule(this))
            .build()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Component> getComponent(componentClass: KClass<T>): T =
        componentMap[componentClass] as T
}