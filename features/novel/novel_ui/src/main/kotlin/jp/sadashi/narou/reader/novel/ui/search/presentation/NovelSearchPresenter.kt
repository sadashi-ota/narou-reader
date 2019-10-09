package jp.sadashi.narou.reader.novel.ui.search.presentation

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import jp.sadashi.narou.reader.novel.domain.NovelRepository
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import javax.inject.Inject
import javax.inject.Named

class NovelSearchPresenter @Inject constructor(
    private val novelRepository: NovelRepository,
    @Named("ui") private val uiScheduler: Scheduler
) : NovelSearchContract.Presentation {
    private var disposable: Disposable? = null
    private var searchWord: String? = null
    private var loadedPageCount: Int = 0
    private var allCount = 0
    private var novelList: MutableList<NovelSummary> = mutableListOf()

    override val selectNovel: (NovelSummary) -> Unit
        get() = {}

    private lateinit var view: NovelSearchContract.View

    override fun setUp(view: NovelSearchContract.View) {
        this.view = view
    }

    override fun search(word: String) {
        searchWord.isNullOrEmpty()
        searchWord = word
        loadedPageCount = 0
        load()
    }

    override fun loadNextPage() {
        load()
    }

    override fun isAllLoaded(): Boolean = (novelList.size == allCount)

    override fun loadedItemCount(): Int = novelList.size

    private fun load() {
        disposable?.let {
            if (!it.isDisposed) {
                return
            }
        }

        val word = searchWord ?: return

        disposable = novelRepository.searchNovel(word = word, page = loadedPageCount + 1)
            .doOnSubscribe {
                view.showProgress()
            }
            .observeOn(uiScheduler)
            .subscribe({ result ->
                if (loadedPageCount == 0) {
                    novelList.clear()
                }
                allCount = result.allCount
                novelList.addAll(result.novelList)
                view.dismissProgress()
                view.clearList()
                view.showList(novelList)
                loadedPageCount++
            }, { throwable ->
                view.dismissProgress()
                view.showErrorDialog(throwable)
            })
    }
}