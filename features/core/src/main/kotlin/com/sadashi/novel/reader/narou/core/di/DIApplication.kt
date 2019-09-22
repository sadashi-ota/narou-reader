package com.sadashi.novel.reader.narou.core.di

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import kotlin.reflect.KClass

abstract class DIApplication : Application() {
    companion object {
        fun get(context: FragmentActivity?): DIApplication {
            val app = context?.applicationContext
            if (app is DIApplication) {
                return app
            } else {
                throw ClassCastException("application must implement ContainerApplication")
            }
        }

        fun get(context: Context): DIApplication {
            val app = context.applicationContext
            if (app is DIApplication) {
                return app
            } else {
                throw ClassCastException("application must implement ContainerApplication")
            }
        }
    }

    abstract fun <T : Component> getComponent(componentClass: KClass<T>): T
}
