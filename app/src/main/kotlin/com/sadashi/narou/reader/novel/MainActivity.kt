package com.sadashi.narou.reader.novel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sadashi.narou.reader.novel.ui.search.view.NovelSearchActivity

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(NovelSearchActivity.callingIntent(this))
        finish()
    }
}