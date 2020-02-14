package jp.sadashi.narou.reader.novel.ui.search.presentation

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import jp.sadashi.narou.reader.novel.domain.NovelRepository
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import jp.sadashi.narou.reader.novel.usecase.SearchNovelUseCase
import javax.inject.Inject
import javax.inject.Named

class NovelSearchPresenter @Inject constructor(
    private val searchNovelUseCase: SearchNovelUseCase,
    @Named("ui") private val uiScheduler: Scheduler
) : NovelSearchContract.Presentation {
    private var disposable: Disposable? = null

    private lateinit var view: NovelSearchContract.View
    private lateinit var viewModel: NovelSearchResultViewModel
    private lateinit var transition: NovelSearchTransition

    override val selectNovel: (NovelSummary) -> Unit
        get() = { transition.moveNovelDetail(it.novelCode) }

    override val bookmarkNovel: (NovelSummary) -> Unit
        get() = {
            val summary = viewModel.bookmark(!it.isBookmark, it)
            view.update(summary)
        }

    override fun setUp(
        view: NovelSearchContract.View,
        viewModel: NovelSearchResultViewModel,
        transition: NovelSearchTransition
    ) {
        this.view = view
        this.viewModel = viewModel
        this.transition = transition
    }

    override fun isExistLoadData(): Boolean = !viewModel.searchWord.isNullOrEmpty()

    override fun search(word: String) {
        load(word, 1)
    }

    override fun refresh() {
        val word =
            viewModel.searchWord ?: throw IllegalStateException("Not doing the first search.")
        load(word, 1)
    }

    override fun loadNextPage() {
        val word =
            viewModel.searchWord ?: throw IllegalStateException("Not doing the first search.")
        load(word, viewModel.getNextPage())
    }

    override fun isAllLoaded(): Boolean = viewModel.isAllLoaded()

    override fun loadedItemCount(): Int = viewModel.novelList.size

    override fun getWord(): String? = viewModel.searchWord

    private fun load(word: String, page: Int) {
        disposable?.let {
            if (!it.isDisposed) {
                return
            }
        }

        disposable = searchNovelUseCase.execute(word = word, page = page)
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