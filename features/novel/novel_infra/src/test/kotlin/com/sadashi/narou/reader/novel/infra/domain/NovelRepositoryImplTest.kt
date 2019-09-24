package com.sadashi.narou.reader.novel.infra.domain

import com.sadashi.narou.reader.novel.domain.NCode
import com.sadashi.narou.reader.novel.domain.dto.NovelDetail
import com.sadashi.narou.reader.novel.domain.dto.NovelSummary
import com.sadashi.narou.reader.novel.infra.api.NovelDetailApiClient
import com.sadashi.narou.reader.novel.infra.api.NovelSearchApiClient
import com.sadashi.narou.reader.novel.infra.api.response.NovelDetailResponse
import com.sadashi.narou.reader.novel.infra.api.response.NovelSearchResult
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
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
        lateinit var mockNovelSummary: NovelSummary
        lateinit var mockSearchResult: NovelSearchResult

        beforeEach {
            mockNovelSummary = mockk()
            mockSearchResult = mockk()
            mockkObject(NovelSummaryConverter)
        }
        afterEach {
            unmockkObject(NovelSummaryConverter)
        }

        context("When succeed to search api") {
            beforeEach {
                every {
                    mockSearchApiClient.searchNovel(word = any())
                } returns Single.just(listOf(mockSearchResult))
            }
            context("When succeed to convert result") {
                beforeEach {
                    every {
                        NovelSummaryConverter.convertToDomainModelForList(any())
                    } returns listOf(mockNovelSummary)
                }
                it("succeed to search") {
                    val single = novelRepositoryImpl.searchNovel("word")
                    single.test().await()
                        .assertNoErrors()
                        .assertComplete()
                        .assertValue(listOf(mockNovelSummary))

                    verify(exactly = 1) {
                        mockSearchApiClient.searchNovel(word = "word")
                        NovelSummaryConverter.convertToDomainModelForList(any())
                    }
                    confirmVerified(mockSearchApiClient, mockDetailApiClient, NovelSummaryConverter)
                }
            }
            context("When failed to convert result") {
                beforeEach {
                    every {
                        NovelSummaryConverter.convertToDomainModelForList(any())
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
                        NovelSummaryConverter.convertToDomainModelForList(any())
                    }
                    confirmVerified(mockSearchApiClient, mockDetailApiClient, NovelSummaryConverter)
                }
            }
        }
        context("When failed to search api") {
            beforeEach {
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
                confirmVerified(mockSearchApiClient, mockDetailApiClient, NovelSummaryConverter)
            }
        }
    }

    describe("#getNovelDetail") {
        lateinit var mockNovelDetail: NovelDetail
        lateinit var mockResponse: NovelDetailResponse
        val ncodeStr = "ncode"

        beforeEach {
            mockNovelDetail = mockk()
            mockResponse = mockk()
            mockkObject(NovelDetailConverter)
        }
        afterEach {
            unmockkObject(NovelDetailConverter)
        }
        context("When succeed to get novel detail api") {
            beforeEach {
                every {
                    mockDetailApiClient.getNovelDetail(ncodeStr, any())
                } returns Single.just(mockResponse)
            }
            context("When succeed to convert") {
                beforeEach {
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
                beforeEach {
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
            beforeEach {
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