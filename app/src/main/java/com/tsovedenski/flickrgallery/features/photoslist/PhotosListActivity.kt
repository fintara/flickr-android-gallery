package com.tsovedenski.flickrgallery.features.photoslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tsovedenski.flickrgallery.R

class PhotosListActivity : AppCompatActivity() {

    private val fragmentTag = "PHOTOS_LIST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos_list)

        val fragment = supportFragmentManager.findFragmentByTag(fragmentTag) as? PhotosListView
            ?: PhotosListView.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.photos_list_container, fragment, fragmentTag)
            .commitNowAllowingStateLoss()

        PhotosListInjector(application).attachPresenter(fragment)
    }
}
