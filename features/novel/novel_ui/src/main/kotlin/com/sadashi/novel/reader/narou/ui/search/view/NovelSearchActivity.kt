package com.sadashi.novel.reader.narou.ui.search.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sadashi.novel.reader.narou.resource.R as CommonR

class NovelSearchActivity: AppCompatActivity() {

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
}