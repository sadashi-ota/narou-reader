package jp.sadashi.narou.reader.novel.ui.search.presentation

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import jp.sadashi.narou.reader.novel.domain.NovelRepository
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import javax.inject.Inject
import javax.inject.Named

class NovelSearchPresenter @Inject constructor(
    private val novelRepository: NovelRepository,
    @Named("ui") private val uiScheduler: Scheduler
) : NovelSearchContract.Presentation {

    private val disposables = CompositeDisposable()

    override val selectNovel: (NovelSummary) -> Unit
        get() = {}

    private lateinit var view: NovelSearchContract.View

    override fun setUp(view: NovelSearchContract.View) {
        this.view = view
    }

    override fun search(word: String) {
        novelRepository.searchNovel(word)
            .doOnSubscribe {
                view.showProgress()
            }
            .observeOn(uiScheduler)
            .subscribe({ summaryList ->
                view.dismissProgress()
                view.clearList()
                view.showList(summaryList)
            }, { throwable ->
                view.dismissProgress()
                view.showErrorDialog(throwable)
            })
            .addTo(disposables)
    }
}