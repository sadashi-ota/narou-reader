package com.sadashi.narou.reader.novel.ui.search.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sadashi.narou.reader.novel.NovelComponent
import com.sadashi.narou.reader.novel.core.di.DIApplication
import com.sadashi.narou.reader.novel.ui.R
import com.sadashi.narou.reader.novel.ui.search.presentation.NovelSearchContract
import kotlinx.android.synthetic.main.fragment_search.toolbar
import javax.inject.Inject

class NovelSearchFragment : Fragment(), NovelSearchContract.View {
    companion object {
        fun newInstance() = NovelSearchFragment()
    }

    @Inject
    internal lateinit var presenter: NovelSearchContract.Presentation

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

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorDialog(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initializeUi() {
        toolbar.also {
            it.setTitle(R.string.app_name)
        }
    }
}