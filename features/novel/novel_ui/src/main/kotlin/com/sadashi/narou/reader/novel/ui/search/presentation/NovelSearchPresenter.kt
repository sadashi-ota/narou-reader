package com.sadashi.narou.reader.novel.ui.search.presentation

import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

internal class NovelSearchPresenter @Inject constructor(
    @Named("ui") private val uiScheduler: Scheduler
) : NovelSearchContract.Presentation {

    private lateinit var view: NovelSearchContract.View

    override fun setUp(view: NovelSearchContract.View) {
        this.view = view
    }

    override fun search() {
    }
}