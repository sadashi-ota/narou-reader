package jp.sadashi.narou.reader.novel.ui.detail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.sadashi.narou.reader.novel.domain.NovelCode
import jp.sadashi.narou.reader.novel.ui.R

class NovelDetailActivity : AppCompatActivity() {

    companion object {
        private val INTENT_KEY =
            NovelDetailActivity::class.qualifiedName ?: "NovelDetailActivity"

        fun callingIntent(context: Context, novelCode: NovelCode): Intent {
            val intent = Intent(context, NovelDetailActivity::class.java)
            intent.putExtra(INTENT_KEY, novelCode.value)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

//        supportFragmentManager.beginTransaction().also {
//            it.replace(R.id.container, NovelSearchFragment.newInstance())
//        }.commitNow()
    }
}