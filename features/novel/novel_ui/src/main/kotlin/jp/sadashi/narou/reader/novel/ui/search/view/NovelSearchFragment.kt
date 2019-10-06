package jp.sadashi.narou.reader.novel.ui.search.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import jp.sadashi.narou.reader.novel.NovelComponent
import jp.sadashi.narou.reader.novel.core.di.DIApplication
import jp.sadashi.narou.reader.novel.core.view.KeyboardUtil
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import jp.sadashi.narou.reader.novel.ui.R
import jp.sadashi.narou.reader.novel.ui.search.presentation.NovelSearchContract
import kotlinx.android.synthetic.main.fragment_search.novelListView
import kotlinx.android.synthetic.main.fragment_search.progressBar
import kotlinx.android.synthetic.main.fragment_search.rootLayout
import kotlinx.android.synthetic.main.fragment_search.searchButton
import kotlinx.android.synthetic.main.fragment_search.searchWordEditText
import kotlinx.android.synthetic.main.fragment_search.toolbar
import javax.inject.Inject

class NovelSearchFragment : Fragment(), NovelSearchContract.View {
    companion object {
        fun newInstance() = NovelSearchFragment()
    }

    @Inject
    internal lateinit var presenter: NovelSearchContract.Presentation

    @Inject
    internal lateinit var adapter: NovelListAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context ?: return

        DIApplication.get(context).getComponent(NovelComponent::class).inject(this)

        presenter.setUp(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUi()
    }

    override fun showList(dtoList: List<NovelSummary>) {
        adapter.collection = dtoList
    }

    override fun clearList() {
        adapter.collection = emptyList()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showErrorDialog(throwable: Throwable) {
        Snackbar.make(rootLayout, R.string.msg_failed_to_search, Snackbar.LENGTH_LONG)
            .setAction(R.string.btn_retry) { search() }
            .show()
    }

    private fun initializeUi() {
        toolbar.also {
            it.setTitle(R.string.app_name)
        }
        searchButton.setOnClickListener {
            search()
            KeyboardUtil.hide(it)
        }

        novelListView.adapter = adapter
        adapter.clickListener = presenter.selectNovel
    }

    private fun search() {
        presenter.search(searchWordEditText.text.toString())
    }
}