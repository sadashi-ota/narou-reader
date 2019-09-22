package com.sadashi.novel.reader.narou

import com.sadashi.novel.reader.narou.core.di.Component
import com.sadashi.novel.reader.narou.core.di.DIApplication
import kotlin.reflect.KClass

class MyApplication : DIApplication() {

    private val componentMap = HashMap<KClass<*>, Component>()

    override fun onCreate() {
        super.onCreate()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Component> getComponent(componentClass: KClass<T>): T =
        componentMap[componentClass] as T
}