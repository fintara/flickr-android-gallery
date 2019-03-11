package com.tsovedenski.flickrgallery.features.photoslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tsovedenski.flickrgallery.R
import com.tsovedenski.flickrgallery.setFragment

class PhotosListActivity : AppCompatActivity() {

    private val fragmentTag = "PHOTOS_LIST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos_list)

        val fragment = supportFragmentManager.findFragmentByTag(fragmentTag) as? PhotosListView
            ?: PhotosListView.newInstance()

        setFragment(fragment, fragmentTag, R.id.photos_list_container)

        PhotosListInjector(application).attachPresenter(fragment)
    }
}
