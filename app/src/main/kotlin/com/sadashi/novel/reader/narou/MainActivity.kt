package com.sadashi.novel.reader.narou

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sadashi.novel.reader.narou.ui.search.view.NovelSearchActivity

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(NovelSearchActivity.callingIntent(this))
        finish()
    }
}