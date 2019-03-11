package com.tsovedenski.flickrgallery.features.viewer

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tsovedenski.flickrgallery.R
import com.tsovedenski.flickrgallery.domain.models.FlickrPhoto
import com.tsovedenski.flickrgallery.setFragment

/**
 * Created by Tsvetan Ovedenski on 11/03/19.
 */
class ViewerActivity : AppCompatActivity() {

    private val fragmentTag = "VIEWER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewer)

        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val photos = intent.getParcelableArrayListExtra<FlickrPhoto>("photos")
        val position = intent.getIntExtra("position", 0)

        val fragment = supportFragmentManager.findFragmentByTag(fragmentTag) as? ViewerView
            ?: ViewerView.newInstance()

        setFragment(fragment, fragmentTag, R.id.viewer_container)

        ViewerInjector(application).attachPresenter(fragment, photos, position)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
