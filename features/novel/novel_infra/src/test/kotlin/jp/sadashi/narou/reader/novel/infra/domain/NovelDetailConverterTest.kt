package jp.sadashi.narou.reader.novel.infra.domain

import jp.sadashi.narou.reader.novel.infra.api.response.NovelDetailResponse
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

internal object NovelDetailConverterTest : Spek({

    val defaultNcode = "novelCode"
    val defaultTitle = "title"
    val defaultSubtitle = "subtitle"
    val defaultPage = 1
    val defaultMaxPage = 2
    val defaultBody = listOf("line 1", "line 2")

    fun createNovelDetailResponse(
        ncode: String? = defaultNcode,
        title: String? = defaultTitle,
        subtitle: String? = defaultSubtitle,
        novelNo: String? = "$defaultPage/$defaultMaxPage",
        body: List<String>? = defaultBody
    ) = NovelDetailResponse().also {
        it.ncode = ncode
        it.title = title
        it.subtitle = subtitle
        it.novelNo = novelNo
        it.body = body
    }

    describe(".convertToDomainModel") {
        it("Convert NovelDetailResponse to NovelDetail") {
            val domainModel = NovelDetailConverter.convertToDomainModel(createNovelDetailResponse())
            assertEquals(defaultNcode, domainModel.ncode.value)
            assertEquals(defaultTitle, domainModel.title)
            assertEquals(defaultSubtitle, domainModel.subtitle)
            assertEquals(defaultPage, domainModel.page)
            assertEquals(defaultMaxPage, domainModel.maxPage)
            assertEquals(defaultBody, domainModel.body)
        }
        context("When novelCode is null") {
            it("throw IllegalArgumentException") {
                assertFailsWith<IllegalArgumentException> {
                    NovelDetailConverter.convertToDomainModel(
                        createNovelDetailResponse(
                            ncode = null
                        )
                    )
                }
            }
        }
        context("When novelCode includes '/'") {
            it("excludes '/' from novelCode") {
                val domainModel = NovelDetailConverter.convertToDomainModel(
                    createNovelDetailResponse(
                        ncode = "n/code"
                    )
                )
                assertEquals("novelCode", domainModel.ncode.value)
            }
        }
        context("When novelNo is null") {
            it("throw IllegalArgumentException") {
                assertFailsWith<IllegalArgumentException> {
                    NovelDetailConverter.convertToDomainModel(
                        createNovelDetailResponse(
                            novelNo = null
                        )
                    )
                }
            }
        }
        context("When novelNo does not split to 2 numbers by '/' ") {
            it("throw IllegalArgumentException") {
                assertFailsWith<IllegalArgumentException> {
                    NovelDetailConverter.convertToDomainModel(
                        createNovelDetailResponse(
                            novelNo = "novelNo"
                        )
                    )
                }
            }
        }
        context("title is null") {
            it("throw IllegalArgumentException") {
                assertFailsWith<IllegalArgumentException> {
                    NovelDetailConverter.convertToDomainModel(
                        createNovelDetailResponse(
                            title = null
                        )
                    )
                }
            }
        }
        context("subtitle is null") {
            it("throw IllegalArgumentException") {
                assertFailsWith<IllegalArgumentException> {
                    NovelDetailConverter.convertToDomainModel(
                        createNovelDetailResponse(
                            subtitle = null
                        )
                    )
                }
            }
        }
        context("NovelDetailResponse.body is null") {
            it("NovelDetail.body is empty list") {
                val domainModel = NovelDetailConverter.convertToDomainModel(
                    createNovelDetailResponse(
                        body = null
                    )
                )
                assertTrue(domainModel.body.isEmpty())
            }
        }
    }
})