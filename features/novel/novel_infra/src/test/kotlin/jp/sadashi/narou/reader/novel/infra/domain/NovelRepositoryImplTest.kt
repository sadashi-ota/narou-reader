package jp.sadashi.narou.reader.novel.infra.domain

import jp.sadashi.narou.reader.novel.domain.NCode
import jp.sadashi.narou.reader.novel.domain.dto.NovelDetail
import jp.sadashi.narou.reader.novel.infra.api.NovelDetailApiClient
import jp.sadashi.narou.reader.novel.infra.api.NovelSearchApiClient
import jp.sadashi.narou.reader.novel.infra.api.response.NovelDetailResponse
import jp.sadashi.narou.reader.novel.infra.api.response.NovelSearchResponse
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import jp.sadashi.narou.reader.novel.domain.dto.NovelSearchResult
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

internal class NovelRepositoryImplTest : Spek({

    val scheduler = Schedulers.trampoline()
    lateinit var mockSearchApiClient: NovelSearchApiClient
    lateinit var mockDetailApiClient: NovelDetailApiClient

    lateinit var novelRepositoryImpl: NovelRepositoryImpl

    beforeEachTest {
        mockSearchApiClient = mockk()
        mockDetailApiClient = mockk()

        novelRepositoryImpl =
            NovelRepositoryImpl(mockSearchApiClient, mockDetailApiClient, scheduler)
    }

    describe("#searchNovel") {
        lateinit var mockNovelSearchResult: NovelSearchResult
        lateinit var mockSearchResponse: NovelSearchResponse

        beforeEachTest {
            mockNovelSearchResult = mockk()
            mockSearchResponse = mockk()
            mockkObject(NovelSearchResultConverter)
        }
        afterEachTest {
            unmockkObject(NovelSearchResultConverter)
        }

        context("When succeed to search api") {
            beforeEachTest {
                every {
                    mockSearchApiClient.searchNovel(word = any())
                } returns Single.just(listOf(mockSearchResponse))
            }
            context("When succeed to convert result") {
                beforeEachTest {
                    every {
                        NovelSearchResultConverter.convertToDomainModel(any())
                    } returns mockNovelSearchResult
                }
                it("succeed to search") {
                    val single = novelRepositoryImpl.searchNovel("word")
                    single.test().await()
                        .assertNoErrors()
                        .assertComplete()
                        .assertValue(mockNovelSearchResult)

                    verify(exactly = 1) {
                        mockSearchApiClient.searchNovel(word = "word")
                        NovelSearchResultConverter.convertToDomainModel(any())
                    }
                    confirmVerified(mockSearchApiClient, mockDetailApiClient, NovelSearchResultConverter)
                }
            }
            context("When failed to convert result") {
                beforeEachTest {
                    every {
                        NovelSearchResultConverter.convertToDomainModel(any())
                    } throws IllegalArgumentException("Failed to convert")
                }
                it("failed to search") {
                    val single = novelRepositoryImpl.searchNovel("word")
                    single.test().await()
                        .assertError(IllegalArgumentException::class.java)
                        .assertNotComplete()
                        .assertNoValues()
                    verify(exactly = 1) {
                        mockSearchApiClient.searchNovel(word = "word")
                        NovelSearchResultConverter.convertToDomainModel(any())
                    }
                    confirmVerified(mockSearchApiClient, mockDetailApiClient, NovelSearchResultConverter)
                }
            }
        }
        context("When failed to search api") {
            beforeEachTest {
                every {
                    mockSearchApiClient.searchNovel(word = any())
                } returns Single.error(Throwable("Failed to search"))
            }
            it("Failed to search") {
                val single = novelRepositoryImpl.searchNovel("word")
                single.test().await()
                    .assertError(Throwable::class.java)
                    .assertNotComplete()
                    .assertNoValues()
                verify(exactly = 1) {
                    mockSearchApiClient.searchNovel(word = "word")
                }
                confirmVerified(mockSearchApiClient, mockDetailApiClient, NovelSearchResultConverter)
            }
        }
    }

    describe("#getNovelDetail") {
        lateinit var mockNovelDetail: NovelDetail
        lateinit var mockResponse: NovelDetailResponse
        val ncodeStr = "ncode"

        beforeEachTest {
            mockNovelDetail = mockk()
            mockResponse = mockk()
            mockkObject(NovelDetailConverter)
        }
        afterEachTest {
            unmockkObject(NovelDetailConverter)
        }
        context("When succeed to get novel detail api") {
            beforeEachTest {
                every {
                    mockDetailApiClient.getNovelDetail(ncodeStr, any())
                } returns Single.just(mockResponse)
            }
            context("When succeed to convert") {
                beforeEachTest {
                    every {
                        NovelDetailConverter.convertToDomainModel(mockResponse)
                    } returns mockNovelDetail
                }
                it("succeed to get novel detail api") {
                    val single = novelRepositoryImpl.getNovelDetail(NCode(ncodeStr), 1)
                    single.test().await()
                        .assertNoErrors()
                        .assertComplete()
                        .assertValue(mockNovelDetail)

                    verify(exactly = 1) {
                        mockDetailApiClient.getNovelDetail(ncodeStr, any())
                        NovelDetailConverter.convertToDomainModel(mockResponse)
                    }
                    confirmVerified(mockSearchApiClient, mockDetailApiClient, NovelDetailConverter)
                }
            }
            context("When failed to convert") {
                beforeEachTest {
                    every {
                        NovelDetailConverter.convertToDomainModel(mockResponse)
                    } throws IllegalArgumentException("Failed to convert")
                }
                it("failed to get novel detail api") {
                    val single = novelRepositoryImpl.getNovelDetail(NCode(ncodeStr), 1)
                    single.test().await()
                        .assertError(IllegalArgumentException::class.java)
                        .assertNotComplete()
                        .assertNoValues()

                    verify(exactly = 1) {
                        mockDetailApiClient.getNovelDetail(ncodeStr, any())
                        NovelDetailConverter.convertToDomainModel(mockResponse)
                    }
                    confirmVerified(mockSearchApiClient, mockDetailApiClient, NovelDetailConverter)
                }
            }
        }
        context("When failed to get novel detail api") {
            beforeEachTest {
                every {
                    mockDetailApiClient.getNovelDetail(ncodeStr, any())
                } returns Single.error(Throwable("Failed to get novel detail api"))
            }
            it("failed to get novel detail api") {
                val single = novelRepositoryImpl.getNovelDetail(NCode(ncodeStr), 1)
                single.test().await()
                    .assertError(Throwable::class.java)
                    .assertNotComplete()
                    .assertNoValues()

                verify(exactly = 1) {
                    mockDetailApiClient.getNovelDetail(ncodeStr, any())
                }
                confirmVerified(mockSearchApiClient, mockDetailApiClient, NovelDetailConverter)
            }
        }
    }
})