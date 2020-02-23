package jp.sadashi.narou.reader.novel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_top.drawerLayout
import kotlinx.android.synthetic.main.activity_top.toolbar

class TopActivity : AppCompatActivity() {
    companion object {
        private const val DRAWER_GRAVITY = GravityCompat.START
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)

        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(DRAWER_GRAVITY)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(DRAWER_GRAVITY)) {
            drawerLayout.closeDrawer(DRAWER_GRAVITY)
        } else {
            super.onBackPressed()
        }
    }
}