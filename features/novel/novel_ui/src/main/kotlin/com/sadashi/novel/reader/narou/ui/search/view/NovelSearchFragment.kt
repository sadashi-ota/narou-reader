package com.sadashi.novel.reader.narou.ui.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sadashi.novel.reader.narou.ui.R
import kotlinx.android.synthetic.main.fragment_search.toolbar

class NovelSearchFragment: Fragment() {

    companion object {
        fun newInstance() = NovelSearchFragment()
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

    private fun initializeUi() {
        toolbar.also {
            it.setTitle(R.string.app_name)
        }
    }
}