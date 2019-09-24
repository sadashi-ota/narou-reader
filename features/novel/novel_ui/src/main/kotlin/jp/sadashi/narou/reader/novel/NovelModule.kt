package jp.sadashi.narou.reader.novel

import android.app.Application
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jp.sadashi.narou.reader.novel.ui.NovelUiModule
import javax.inject.Named

@Module(
    includes = [
        NovelUiModule::class
    ]
)
class NovelModule(private val application: Application) {
    @Provides
    fun provideApplication(): Application = application

    @Named("ui")
    @Provides
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Named("io")
    @Provides
    fun provideIoScheduler(): Scheduler = Schedulers.io()
}