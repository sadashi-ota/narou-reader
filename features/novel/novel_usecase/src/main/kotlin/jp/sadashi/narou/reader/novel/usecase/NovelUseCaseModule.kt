package jp.sadashi.narou.reader.novel.usecase

import dagger.Module
import dagger.Provides
import jp.sadashi.narou.reader.novel.usecase.impl.SearchNovelUseCaseImpl
import javax.inject.Singleton

@Module
class NovelUseCaseModule {
    @Provides
    fun provideSearchNovelUseCase(impl: SearchNovelUseCaseImpl): SearchNovelUseCase = impl
}
