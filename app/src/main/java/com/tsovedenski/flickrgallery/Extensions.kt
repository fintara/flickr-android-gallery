package com.tsovedenski.flickrgallery

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by Tsvetan Ovedenski on 10/03/19.
 */
fun AppCompatActivity.setFragment(fragment: Fragment, tag: String, containerViewId: Int) =
    supportFragmentManager.beginTransaction()
        .replace(containerViewId, fragment, tag)
        .commitNowAllowingStateLoss()