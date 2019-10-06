package jp.sadashi.narou.reader.novel.infra

import dagger.Module
import dagger.Provides
import jp.sadashi.narou.reader.novel.domain.NovelRepository
import jp.sadashi.narou.reader.novel.infra.api.NovelDetailApiClient
import jp.sadashi.narou.reader.novel.infra.api.NovelDetailApiClientFactory
import jp.sadashi.narou.reader.novel.infra.api.NovelSearchApiClient
import jp.sadashi.narou.reader.novel.infra.api.NovelSearchApiClientFactory
import jp.sadashi.narou.reader.novel.infra.domain.NovelRepositoryImpl
import javax.inject.Singleton

@Module
class NovelInfraModule {
    @Provides
    fun provideNovelSearchApiClient(): NovelSearchApiClient = NovelSearchApiClientFactory.create()

    @Provides
    fun provideNovelDetailApiClient(): NovelDetailApiClient = NovelDetailApiClientFactory.create()

    @Singleton
    @Provides
    fun provideNovelRepository(impl: NovelRepositoryImpl): NovelRepository = impl
}
