package jp.sadashi.narou.reader.novel.ui.search.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.sadashi.narou.reader.novel.domain.NovelCode
import jp.sadashi.narou.reader.novel.ui.detail.view.NovelDetailActivity
import jp.sadashi.narou.reader.novel.ui.search.presentation.NovelSearchTransition
import jp.sadashi.narou.reader.novel.resource.R as CommonR

class NovelSearchActivity: AppCompatActivity(), NovelSearchTransition {

    companion object {
        fun callingIntent(context: Context) = Intent(context, NovelSearchActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(CommonR.layout.activity_container)

        supportFragmentManager.beginTransaction().also {
            it.replace(CommonR.id.container, NovelSearchFragment.newInstance())
        }.commitNow()
    }

    override fun moveNovelDetail(novelCode: NovelCode) {
        val intent = NovelDetailActivity.callingIntent(this, novelCode)
        startActivity(intent)
    }
}