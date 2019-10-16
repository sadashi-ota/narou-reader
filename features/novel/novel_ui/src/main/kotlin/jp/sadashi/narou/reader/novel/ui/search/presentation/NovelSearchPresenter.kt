package jp.sadashi.narou.reader.novel.ui.search.presentation

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import jp.sadashi.narou.reader.novel.domain.NovelRepository
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import javax.inject.Inject
import javax.inject.Named

class NovelSearchPresenter @Inject constructor(
    private val novelRepository: NovelRepository,
    @Named("ui") private val uiScheduler: Scheduler
) : NovelSearchContract.Presentation {
    private var disposable: Disposable? = null

    private lateinit var view: NovelSearchContract.View
    private lateinit var viewModel: NovelSearchResultViewModel

    override val selectNovel: (NovelSummary) -> Unit
        get() = {}

    override fun setUp(
        view: NovelSearchContract.View,
        viewModel: NovelSearchResultViewModel
    ) {
        this.view = view
        this.viewModel = viewModel
    }

    override fun isExistLoadData(): Boolean = viewModel.searchWord.isNullOrEmpty()

    override fun search(word: String) {
        load(word, 1)
    }

    override fun loadNextPage() {
        val word =
            viewModel.searchWord ?: throw IllegalStateException("Not doing the first search.")
        load(word, viewModel.getNextPage())
    }

    override fun isAllLoaded(): Boolean = viewModel.isAllLoaded()

    override fun loadedItemCount(): Int = viewModel.novelList.size

    private fun load(word: String, page: Int) {
        disposable?.let {
            if (!it.isDisposed) {
                return
            }
        }

        disposable = novelRepository.searchNovel(word = word, page = page)
            .doOnSubscribe {
                view.showProgress()
            }
            .observeOn(uiScheduler)
            .subscribe({ result ->
                if (page == 0) {
                    viewModel.clear()
                }
                viewModel.add(word, result)

                view.dismissProgress()
                view.clearList()
                view.showList(viewModel.novelList)
            }, { throwable ->
                view.dismissProgress()
                view.showErrorDialog(throwable)
            })
    }
}