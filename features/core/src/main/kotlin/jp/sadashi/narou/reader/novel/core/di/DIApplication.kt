package jp.sadashi.narou.reader.novel.core.di

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

    abstract fun <T : Injector> getInjector(injectorClass: KClass<T>): T
}
