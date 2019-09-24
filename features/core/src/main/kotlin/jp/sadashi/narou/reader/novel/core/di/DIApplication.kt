package jp.sadashi.narou.reader.novel.core.di

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import kotlin.reflect.KClass

abstract class DIApplication : Application() {
    companion object {
        fun get(context: FragmentActivity?): jp.sadashi.narou.reader.novel.core.di.DIApplication {
            val app = context?.applicationContext
            if (app is jp.sadashi.narou.reader.novel.core.di.DIApplication) {
                return app
            } else {
                throw ClassCastException("application must implement ContainerApplication")
            }
        }

        fun get(context: Context): jp.sadashi.narou.reader.novel.core.di.DIApplication {
            val app = context.applicationContext
            if (app is jp.sadashi.narou.reader.novel.core.di.DIApplication) {
                return app
            } else {
                throw ClassCastException("application must implement ContainerApplication")
            }
        }
    }

    abstract fun <T : jp.sadashi.narou.reader.novel.core.di.Component> getComponent(componentClass: KClass<T>): T
}
