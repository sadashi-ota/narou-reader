package jp.sadashi.narou.reader.novel.ui.search.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import jp.sadashi.narou.reader.novel.core.di.DIApplication
import jp.sadashi.narou.reader.novel.core.view.KeyboardUtil
import jp.sadashi.narou.reader.novel.domain.dto.NovelSummary
import jp.sadashi.narou.reader.novel.ui.R
import jp.sadashi.narou.reader.novel.ui.search.NovelInjector
import jp.sadashi.narou.reader.novel.ui.search.presentation.NovelSearchContract
import jp.sadashi.narou.reader.novel.ui.search.presentation.NovelSearchResultViewModel
import jp.sadashi.narou.reader.novel.ui.search.presentation.NovelSearchTransition
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class NovelSearchFragment : Fragment(), NovelSearchContract.View {
    companion object {
        private val KEY_RESTORE = NovelSearchFragment::class.qualifiedName ?: "NovelSearchFragment"
        private const val REQUEST_PRELOAD_NUM = 10

        fun newInstance() = NovelSearchFragment()
    }

    private val viewModel: NovelSearchResultViewModel by viewModels()

    @Inject
    internal lateinit var presenter: NovelSearchContract.Presentation

    @Inject
    internal lateinit var adapter: NovelListAdapter

    private lateinit var search: SearchView
    private lateinit var searchMenu: MenuItem

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
            val lastVisibleItemCount = layoutManager!!.findLastVisibleItemPosition() + 1

            if (!presenter.isAllLoaded() && presenter.loadedItemCount() <= lastVisibleItemCount + REQUEST_PRELOAD_NUM) {
                presenter.loadNextPage()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DIApplication.get(context).getInjector(NovelInjector::class).inject(this)

        val screenTransition = (context as? NovelSearchTransition)
            ?: throw ClassCastException("must cast NovelSearchTransition")

//        val viewModel = activity?.let {
//            ViewModelProviders.of(it).get(NovelSearchResultViewModel::class.java)
//        } ?: throw IllegalStateException("Activity is null.")

        presenter.setUp(this, viewModel, screenTransition)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUi()
        restore(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_RESTORE, presenter.getWord())
    }

    override fun showList(dtoList: List<NovelSummary>) {
        adapter.collection = dtoList.toMutableList()
    }

    override fun update(summary: NovelSummary) {
        adapter.updateItem(summary)
    }

    override fun clearList() {
        adapter.collection = mutableListOf()
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
            it.inflateMenu(R.menu.search)
            it.setNavigationIcon(R.drawable.ic_search_24dp)
            it.setNavigationOnClickListener { toggleSearchView() }
            it.setOnClickListener { toggleSearchView() }
        }

        searchMenu = toolbar.menu.findItem(R.id.menu_search)
        search = (searchMenu.actionView as SearchView).also {
            it.setIconifiedByDefault(false)

            val icon = it.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
            icon.setImageDrawable(null)

            it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(word: String): Boolean {
                    search()
                    return true
                }

                override fun onQueryTextChange(s: String): Boolean {
                    return false
                }
            })
        }

        adapter.also {
            it.clickListener = presenter.selectNovel
            it.clickBookmarkListener = presenter.bookmarkNovel
        }

        novelListView.also {
            it.adapter = adapter
            it.addOnScrollListener(scrollListener)
        }
    }

    private fun search() {
        presenter.search(search.query.toString())
        hideSearchView()
    }

    private fun restore(savedInstanceState: Bundle?) {
        if (presenter.isExistLoadData()) {
            presenter.refresh()
            return
        }

        savedInstanceState?.let {
            val word = savedInstanceState.getString(KEY_RESTORE, "")
            word.isEmpty() && return@let

            presenter.search(word)
        }
    }

    private fun toggleSearchView() {
        if (searchMenu.isVisible) {
            hideSearchView()
        } else {
            showSearchView()
        }
    }

    private fun showSearchView() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        search.isIconified = false
        searchMenu.isVisible = true
        search.setQuery(presenter.getWord(), false)
    }

    private fun hideSearchView() {
        toolbar.setNavigationIcon(R.drawable.ic_search_24dp)
        search.clearFocus()
        search.isIconified = true
        KeyboardUtil.hide(search)
        searchMenu.isVisible = false
    }
}