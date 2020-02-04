@file:Suppress("SpellCheckingInspection")

package jp.sadashi.narou.reader.novel.infra.domain

import jp.sadashi.narou.reader.novel.infra.api.response.NovelSearchResponse
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal object NovelSummaryConverterTest : Spek({

    val timestamp = "2019-01-01 00:00:00"

    fun convertTimestamp(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
    }

    fun createNovelSearchResult(
        ncode: String = "novelCode",
        title: String = "title",
        writer: String = "writer",
        story: String = "story",
        all_point: Int = 200,
        review_cnt: Int = 100,
        fav_novel_cnt: Int = 50,
        novelupdated_at: String = timestamp,
        allcount: Int? = null,
        all_hyoka_cnt: Int? = null,
        biggenre: Int? = null,
        end: Int? = null,
        general_all_no: Int? = null,
        general_firstup: String? = null,
        general_lastup: String? = null,
        genre: Int? = null,
        gensaku: String? = null,
        global_point: Int? = null,
        isbl: Int? = null,
        isgl: Int? = null,
        isr15: Int? = null,
        isstop: Int? = null,
        istenni: Int? = null,
        istensei: Int? = null,
        iszankoku: Int? = null,
        kaiwaritu: Int? = null,
        keyword: String? = null,
        length: Int? = null,
        novel_type: Int? = null,
        pc_or_k: Int? = null,
        sasie_cnt: Int? = null,
        time: Int? = null,
        updated_at: String? = null,
        userid: Int? = null
    ) = NovelSearchResponse(
        ncode,
        title,
        writer,
        story,
        all_point,
        fav_novel_cnt,
        review_cnt,
        novelupdated_at,
        allcount,
        all_hyoka_cnt,
        biggenre,
        end,
        general_all_no,
        general_firstup,
        general_lastup,
        genre,
        gensaku,
        global_point,
        isbl,
        isgl,
        isr15,
        isstop,
        istenni,
        istensei,
        iszankoku,
        kaiwaritu,
        keyword,
        length,
        novel_type,
        pc_or_k,
        sasie_cnt,
        time,
        updated_at,
        userid
    )

    describe(".convertToDomainModelForList") {
        it("Convert NovelSearchResponse to NovelSummary") {
            val result = NovelSummaryConverter.convertToDomainModelForList(listOf(createNovelSearchResult()))
            assertEquals(1, result.size)
            assertEquals("novelCode", result[0].novelCode.value)
            assertEquals("title", result[0].title)
            assertEquals("writer", result[0].writer)
            assertEquals(200, result[0].totalRating)
            assertEquals(50, result[0].reviewCount)
            assertEquals(100, result[0].bookmarkCount)
            assertEquals(timestamp,  convertTimestamp(result[0].novelUpdatedAt))
        }
    }
    describe(".convertToDomainModel") {
        it("Convert NovelSearchResponse to NovelSummary") {
            val result = NovelSummaryConverter.convertToDomainModel(createNovelSearchResult())
            assertEquals("novelCode", result.novelCode.value)
            assertEquals("title", result.title)
            assertEquals("writer", result.writer)
            assertEquals(200, result.totalRating)
            assertEquals(50, result.reviewCount)
            assertEquals(100, result.bookmarkCount)
            assertEquals(timestamp,  convertTimestamp(result.novelUpdatedAt))
        }
        context("When novelupdated_at is invalid format") {
            it("throw ParseException") {
                assertFailsWith<ParseException> {
                    NovelSummaryConverter.convertToDomainModel(
                        createNovelSearchResult(novelupdated_at = "2019/01/01 00:00:00")
                    )
                }
            }
        }
    }
})
