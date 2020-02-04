package jp.sadashi.narou.reader.novel.usecase.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import jp.sadashi.narou.reader.novel.domain.NovelRepository
import jp.sadashi.narou.reader.novel.domain.dto.NovelSearchResult
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

internal class SearchNovelUseCaseImplTest : Spek({

    lateinit var repository: NovelRepository
    lateinit var result: NovelSearchResult

    describe("#execute") {
        beforeEachTest {
            repository = mockk()
        }
        context("When repository is success.") {
            beforeEachTest {
                result = mockk()
                every {
                    repository.searchNovel(any(), any(), any())
                } returns Single.just(result)
            }

            it("succeed to call") {
                val useCase = SearchNovelUseCaseImpl(repository)
                useCase.execute(SEARCH_ANY_WORD, ANY_PAGE_NUM)
                    .test().await()
                    .assertNoErrors()
                    .assertComplete()
                    .assertValue(result)

                verify(exactly = 1) {
                    repository.searchNovel(SEARCH_ANY_WORD, ANY_PAGE_NUM)
                }
            }
        }

        context("When repository throw errors") {
            beforeEachTest {
                every {
                    repository.searchNovel(any(), any(), any())
                } returns Single.error(DUMMY_EXCEPTION)
            }
            it("failed to call") {
                val useCase = SearchNovelUseCaseImpl(repository)
                useCase.execute(SEARCH_ANY_WORD, ANY_PAGE_NUM)
                    .test().await()
                    .assertError(DUMMY_EXCEPTION)
                    .assertNotComplete()
                    .assertNoValues()
            }
        }
    }
}) {
    companion object {
        private const val SEARCH_ANY_WORD = "any word"
        private const val ANY_PAGE_NUM = 1
        private val DUMMY_EXCEPTION = Throwable("dummy exception")
    }
}